package com.zzmall.api.common.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Connor
 * @Date 2019/05/28 14:56
 */
@Data
public class NativeRequestDTO {

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 金额 单位：分
     */
    private Integer total_fee;

    /**
     * 订单号
     */
    private String out_trade_no;

    /**
     * 订单标题
     */
    private String body;

    /**
     * 数据签名
     */
    private String sign;


    public void setTotalFee(BigDecimal bigDecimal) {
        this.total_fee = bigDecimal.multiply(new BigDecimal(100)).intValue();
    }
}
