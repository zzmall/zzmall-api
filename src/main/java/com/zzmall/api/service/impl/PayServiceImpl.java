package com.zzmall.api.service.impl;

import com.zzmall.api.common.config.PayjsProperties;
import com.zzmall.api.common.dto.NativeRequestDTO;
import com.zzmall.api.common.dto.NativeResponseDTO;
import com.zzmall.api.common.dto.PayCheckDTO;
import com.zzmall.api.common.dto.PayCheckResponseDTO;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.OrderPayVO;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.Order;
import com.zzmall.api.pojo.PayInfo;
import com.zzmall.api.repositor.OrderRepository;
import com.zzmall.api.repositor.PayInfoRepository;
import com.zzmall.api.service.PayService;
import com.zzmall.api.service.PayjsService;
import com.zzmall.api.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @Author Connor
 * @Date 2019/05/28 17:23
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PayInfoRepository payInfoRepository;

    @Autowired
    private PayjsProperties payjsProperties;

    @Autowired
    private PayjsService payjsService;

    @Override
    public OrderPayVO pay(BigInteger orderNo, Integer userId) {

        //1.查询该用户下面是否拥有此订单

        List<Order> userOrderList = orderRepository.findAllByUserId(userId);

        Optional<Order> order = userOrderList.stream().filter(i -> i.getOrderNo().compareTo(orderNo) == 0).findFirst();
        if (!order.isPresent()) {
            throw new ApiException(ResponseMessage.ORDER_ID_NULL);
        }

        //2.检查该订单支付已经支付
        List<PayInfo> userPayInfoList = payInfoRepository.findAllByUserId(userId);
        Optional<PayInfo> payInfo = userPayInfoList.stream().filter(i -> i.getOrderNo().compareTo(orderNo) == 0).findFirst();
        if (payInfo.isPresent()) {
            throw new ApiException(ResponseMessage.ORDER_PAY_REPEAT_FAIL);
        }

        //3.支付
        NativeRequestDTO requestDTO = new NativeRequestDTO();
        requestDTO.setMchid(payjsProperties.getMchid());
        requestDTO.setBody("我是订单");
        requestDTO.setOut_trade_no(orderNo.toString());
        requestDTO.setTotalFee(order.get().getPayment());
        requestDTO.setSign(KeyUtil.getSign(requestDTO, payjsProperties.getKey()));
        NativeResponseDTO nativeResponseDTO = payjsService.nativePay(requestDTO);

        if (nativeResponseDTO == null) {
            throw new ApiException(ResponseMessage.ORDER_PAY_FAIL);
        }

        return new OrderPayVO(nativeResponseDTO.getOut_trade_no(), nativeResponseDTO.getQrcode());
    }

    @Override
    public Boolean isPayCheck(BigInteger orderNo, String payjsOrderId, Integer userId) {

        //1.查询该用户下面是否拥有此订单

        List<Order> userOrderList = orderRepository.findAllByUserId(userId);

        Optional<Order> order = userOrderList.stream().filter(i -> i.getOrderNo().compareTo(orderNo) == 0).findFirst();
        if (!order.isPresent()) {
            throw new ApiException(ResponseMessage.ORDER_ID_NULL);
        }

        //2.检查该订单支付已经支付
        List<PayInfo> userPayInfoList = payInfoRepository.findAllByUserId(userId);
        Optional<PayInfo> payInfo = userPayInfoList.stream().filter(i -> i.getOrderNo().compareTo(orderNo) == 0).findFirst();
        if (payInfo.isPresent()) {
            throw new ApiException(ResponseMessage.ORDER_PAY_REPEAT_FAIL);
        }

        //3.查询payjs订单支付信息
        PayCheckDTO payCheckDTO = new PayCheckDTO();
        payCheckDTO.setPayjs_order_id(payjsOrderId);
        payCheckDTO.setSign(KeyUtil.getSign(payCheckDTO, payjsProperties.getKey()));

        PayCheckResponseDTO check = payjsService.check(payCheckDTO);

        //如果请求失败
        if (check.getReturn_code() == 0) {
            throw new ApiException(ResponseMessage.FAIL);
        }

        //如果订单状态为支付失败
        if (check.getStatus() == 0) {
            return Boolean.FALSE;
        }

        //创建payinfo
        PayInfo payInfoObject = new PayInfo();
        payInfoObject.setUserId(userId);
        payInfoObject.setOrderNo(orderNo);
        payInfoObject.setPlatformNumber(check.getTransaction_id());
        payInfoObject.setPlatformStatus("SUCCESS");

        //将该订单的支付信息保存到数据库中
        payInfoRepository.save(payInfoObject);

        return Boolean.TRUE;
    }
}
