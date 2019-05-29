package com.zzmall.api.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author Connor
 * @Date 2019/05/28 17:01
 */
@Data
public class PayCheckResponseDTO {

    /**
     * 请求状态
     */
    private Integer return_code;

    /**
     * 用户端订单号
     */
    private String out_trade_no;


    /**
     * payjs订单号
     */
    private String payjs_order_id;

    /**
     * 微信显示订单号
     */
    private String transaction_id;

    /**
     * 支付状态
     */
    private Integer status;

    /**
     * 订单金额
     */
    private Integer total_fee;

    /**
     * 订单支付时间
     */
    private Date paid_time;
}
