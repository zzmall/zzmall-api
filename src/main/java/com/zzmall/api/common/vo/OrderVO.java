package com.zzmall.api.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zzmall.api.common.constant.OrderConstant;
import com.zzmall.api.pojo.OrderItem;
import com.zzmall.api.pojo.Shipping;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/22 19:26
 */
@Data
public class OrderVO {

    /**
     * 订单编号
     */
    private BigInteger orderNo;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收货地址id
     */
    private Integer shippingId;

    /**
     * 付款金额
     */
    private BigDecimal payment;

    /**
     * 支付方式
     */
    private Integer paymentType = OrderConstant.PAYMENT_TYPE_WXPAY;

    /**
     * 运费 单位是元
     */
    private Integer postage;

    /**
     * 状态
     * 默认 未付款
     */
    private Integer status = OrderConstant.STATUS_UNPAID;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date sendTime;

    /**
     * 交易完成时间
     */
    private Date endTime;

    /**
     * 交易关闭时间
     */
    private Date closeTime;

    /**
     * 订单包含的所有商品
     */
    @JsonProperty(value = "orderItemVoList")
    private List<OrderItem> orderItemList;

    /**
     * 收货地址
     */
    private Shipping shipping;

}
