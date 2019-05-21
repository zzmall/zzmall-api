package com.zzmall.api.common.form;

import io.netty.util.internal.StringUtil;
import lombok.Data;

/**
 * @Author Connor
 * @Date 2019/05/21 19:52
 */
@Data
public class ShippingForm {

    /**
     * 收货地址id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人手机
     */
    private String receiverMobile;

    /**
     * 省
     */
    private String receiverProvince;

    /**
     * 市
     */
    private String receiverCity;

    /**
     * 区
     */
    private String receiverDistrict = StringUtil.EMPTY_STRING;

    /**
     * 详细地址
     */
    private String receiverAddress;

    /**
     * 邮政编码
     */
    private String receiverZip;
}
