package com.zzmall.api.service;

import com.zzmall.api.common.vo.OrderPayVO;

import java.math.BigInteger;

/**
 * @Author Connor
 * @Date 2019/05/28 15:15
 */
public interface PayService {


    /**
     * 支付
     *
     * @param orderNo
     * @return
     */
    OrderPayVO pay(BigInteger orderNo, Integer userId);

    /**
     * 验证订单是否已经支付
     *
     * @param orderNo
     * @param userId
     * @return
     */
    Boolean isPayCheck(BigInteger orderNo, String payjsOrderId, Integer userId);

}
