package com.zzmall.api.service;

import com.zzmall.api.common.vo.OrderItemVO;
import com.zzmall.api.common.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;

/**
 * @Author Connor
 * @Date 2019/05/22 20:00
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param shippingId
     * @param userId
     * @return
     */
    OrderVO create(Integer shippingId, Integer userId);

    /**
     * 获取订单内商品信息
     *
     * @param orderNo
     * @param userId
     * @return
     */
    OrderItemVO getOrderProduct(BigInteger orderNo, Integer userId);

    /**
     * 取消订单
     *
     * @param orderNo
     * @param userId
     */
    void cancel(BigInteger orderNo, Integer userId);

    /**
     * 查询订单详情
     *
     * @param orderNo
     * @param userId
     * @return
     */
    OrderVO detail(BigInteger orderNo, Integer userId);

    /**
     * 获取该用户的所有订单信息
     *
     * @param userId
     * @return
     */
    Page<OrderVO> list(Pageable pageable, Integer userId);

}
