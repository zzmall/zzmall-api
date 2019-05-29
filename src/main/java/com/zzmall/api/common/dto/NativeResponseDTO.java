package com.zzmall.api.common.dto;

import lombok.Data;

/**
 * @Author Connor
 * @Date 2019/05/28 16:10
 */
@Data
public class NativeResponseDTO {

    /**
     * 请求状态
     */
    private Integer return_code;

    /**
     * PAYJS 平台订单号
     */
    private String payjs_order_id;

    /**
     * 用户生成的订单号
     */
    private String out_trade_no;

    /**
     * 付款二维码地址
     */
    private String qrcode;



}
