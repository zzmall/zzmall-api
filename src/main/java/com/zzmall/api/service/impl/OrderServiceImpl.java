package com.zzmall.api.service.impl;

import com.zzmall.api.common.constant.OrderConstant;
import com.zzmall.api.common.dto.CartProductDTO;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.CartProductVO;
import com.zzmall.api.common.vo.OrderItemVO;
import com.zzmall.api.common.vo.OrderVO;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.*;
import com.zzmall.api.repositor.*;
import com.zzmall.api.service.CartService;
import com.zzmall.api.service.OrderService;
import com.zzmall.api.util.BeanUtils;
import com.zzmall.api.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author Connor
 * @Date 2019/05/22 22:44
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PayInfoRepository payInfoRepository;

    @Override
    public OrderVO create(Integer shippingId, Integer userId) {

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = null;

        //1.校验该收货地址是否存在
        List<Shipping> shippingList = shippingRepository.findAllByUserId(userId);
        boolean isShipping = shippingList.stream().filter(i -> i.getId() == shippingId).findFirst().isPresent();
        if (!isShipping) {
            throw new ApiException(ResponseMessage.SHIPPING_ID_NULL);
        }

        //2.获取该用户的购物车列表
        CartProductVO cartProductVO = cartService.getCartByUserId(userId);

        //3.创建订单
        Order order = new Order(userId,shippingId,cartProductVO.getCartTotalPrice());
        order.setOrderNo(BigInteger.valueOf(KeyUtil.genUniqueKey()));
        Order result = orderRepository.save(order);
        if (result == null) {
            throw new ApiException(ResponseMessage.ORDER_CREATE_FAIL);
        }
        //5.封装该订单信息
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(result, orderVO);
        for (CartProductDTO c : cartProductVO.getProductList()) {
            orderItem = new OrderItem();
            BeanUtils.copyProperties(c, orderItem);
            orderItem.setOrderNo(result.getOrderNo());
            orderItem.setProductImage(c.getProductMainImage());
            orderItem.setCurrentUnitPrice(c.getProductPrice());
            orderItem.setTotalPrice(c.getProductTotalPrice());
            orderItemList.add(orderItem);
        }
        //6.添加订单字表
        orderItemRepository.saveAll(orderItemList);
        orderVO.setOrderItemList(orderItemList);

        return orderVO;
    }

    @Override
    public OrderItemVO getOrderProduct(BigInteger orderNo, Integer userId) {

        List<OrderItem> orderItemsByUser = orderItemRepository.findAllByUserId(userId);
        List<OrderItem> orderItems = orderItemsByUser.stream()
                .filter(i -> i.getOrderNo().compareTo(orderNo) == 0).collect(Collectors.toList());

        if (orderItems.size() <= 0) {
            throw new ApiException(ResponseMessage.FAIL);
        }

        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setOrderItemList(orderItems);

        //计算商品总价
        BigDecimal totalPrice = orderItems.stream()
                .map(OrderItem::getCurrentUnitPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        orderItemVO.setProductTotalPrice(totalPrice);

        return orderItemVO;
    }

    @Override
    public void cancel(BigInteger orderNo, Integer userId) {
        List<Order> orderList = orderRepository.findAllByUserId(userId);

        //检验该用户下是否存在此订单
        Optional<Order> order = orderList.stream().filter(i -> i.getOrderNo().compareTo(orderNo) == 0).findFirst();
        if (!order.isPresent()) {
            throw new ApiException(ResponseMessage.ORDER_ID_NULL);
        }

        //判断该订单是否已经支付
        //查询支付信息表是否存在该订单
        List<PayInfo> payInfoList = payInfoRepository.findAllByUserId(userId);
        Optional<PayInfo> payInfo = payInfoList.stream().filter(i -> i.getOrderNo().compareTo(orderNo) == 0).findFirst();
        if (payInfo.isPresent()) {
            throw new ApiException(ResponseMessage.ORDER_CANCEL_PAY_FAIL);
        }
        //修改状态为已取消
        order.get().setPaymentType(OrderConstant.STATUS_CANCEL);
        //保存修改
        orderRepository.save(order.get());
    }

    @Override
    public OrderVO detail(BigInteger orderNo, Integer userId) {

        //1.查询该订单是否存在
        List<Order> orderList = orderRepository.findAllByUserId(userId);
        Optional<Order> order = orderList.stream().filter(i -> i.getOrderNo().compareTo(orderNo) == 0).findFirst();
        if (!order.isPresent()) {
            throw new ApiException(ResponseMessage.ORDER_ID_NULL);
        }

        //2.获取该订单下所有商品
        List<OrderItem> orderItemList = orderItemRepository.findAllByUserId(userId);
        List<OrderItem> itemList = orderItemList.stream().filter(i -> i.getOrderNo().compareTo(orderNo) == 0).collect(Collectors.toList());

        //3.封装订单信息
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderItemList(itemList);
        BeanUtils.copyProperties(order.get(), orderVO);
        ////1.查询购物车信息
        Optional<Shipping> shipping = shippingRepository.findById(orderVO.getShippingId());
        if (!shipping.isPresent()) {
            throw new ApiException(ResponseMessage.FAIL);
        }
        orderVO.setShipping(shipping.get());

        return orderVO;
    }

    @Override
    public Page<OrderVO> list(Pageable pageable, Integer userId) {

        List<Order> orderList = orderRepository.findAllByUserId(userId);

        List<OrderVO> orderVOList = new ArrayList<>();

        OrderVO orderVO = null;

        for (Order order : orderList) {
            orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);
            orderVO.setOrderItemList(orderItemRepository.findAllByOrderNoAndUserId(order.getOrderNo(), userId));
            orderVO.setShipping(shippingRepository.findById(orderVO.getShippingId()).get());
            orderVOList.add(orderVO);
        }
        return new PageImpl<OrderVO>(orderVOList, pageable, orderVOList.size());
    }
}
