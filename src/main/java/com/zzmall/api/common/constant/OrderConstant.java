package com.zzmall.api.common.constant;

/**
 * @Author Connor
 * @Date 2019/05/22 19:31
 *
 * 订单通用常量类
 */
public interface OrderConstant {

    /**
     * 订单状态
     */
    Integer STATUS_CANCEL = 0; //已取消
    Integer STATUS_UNPAID = 10; //未付款
    Integer STATUS_PAID = 20; //已支付
    Integer STATUS_SHIPPED = 30; //已发货
    Integer STATUS_TRANSACTION_SUCCESS = 50; //交易成功
    Integer STATUS_TRANSACTION_CLOSED = 60; //交易关闭


    /**
     * 支付类型
     */
    Integer PAYMENT_TYPE_WXPAY = 1; //微信支付
    Integer PAYMENT_TYPE_ALIPAY = 2; //支付宝支付

}
