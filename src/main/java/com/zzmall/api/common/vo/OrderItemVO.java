package com.zzmall.api.common.vo;

import com.zzmall.api.pojo.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/22 20:09
 */
@Data
public class OrderItemVO {

    /**
     * 商品列表
     */
    private List<OrderItem> orderItemList;

    /**
     * 商品图片host
     */
    private String imageHost;

    /**
     * 订单内商品总价
     */
    private BigDecimal productTotalPrice;
}
