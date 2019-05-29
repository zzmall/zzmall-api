package com.zzmall.api.common.vo;

import lombok.Data;

/**
 * @Author Connor
 * @Date 2019/05/28 17:21
 */
@Data
public class OrderPayVO {

    /**
     * PAYJS 平台订单号
     */
    private String payjs_order_id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 付款二维码地址
     */
    private String qrPath;

    public OrderPayVO() {
    }

    public OrderPayVO(String orderNo, String qrPath) {
        this.orderNo = orderNo;
        this.qrPath = qrPath;
    }
}
