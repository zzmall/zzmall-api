package com.zzmall.api.controller;

import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.OrderPayVO;
import com.zzmall.api.common.vo.ResponseVO;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.User;
import com.zzmall.api.service.PayService;
import com.zzmall.api.util.SecurityUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Map;

/**
 * @Author Connor
 * @Date 2019/05/21 21:24
 */
@RestController
@RequestMapping("/order")
public class PortalPayController {

    @Autowired
    private PayService payService;

    @RequestMapping("/pay.do")
    public ResponseVO<OrderPayVO> pay(BigInteger orderNo, HttpServletRequest request) {

        User user = SecurityUtil.isLogin(request.getSession());

        if (orderNo == null) {
            throw new ApiException(ResponseMessage.ORDER_ID_NULL);
        }

        OrderPayVO pay = payService.pay(orderNo, user.getId());
        return ResponseVO.success(pay);

    }

    @RequestMapping("/query_order_pay_status.do")
    public ResponseVO<Boolean> queryOrderPayStatus(BigInteger orderNo, String payjsOrderId, HttpServletRequest request) {
        User user = SecurityUtil.isLogin(request.getSession());

        if (orderNo == null) {
            throw new ApiException(ResponseMessage.ORDER_ID_NULL);
        }

        Boolean payCheck = payService.isPayCheck(orderNo, payjsOrderId, user.getId());
        return ResponseVO.success(payCheck);

    }


}
