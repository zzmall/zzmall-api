package com.zzmall.api.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zzmall.api.common.dto.CartProductDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/20 16:08
 */
@Data
public class CartProductVO {

    /**
     * 商品列表
     */
    @JsonProperty("cartProductVoList")
    private List<CartProductDTO> productList;

    /**
     * 是否全选
     */
    private Boolean allChecked = Boolean.TRUE;

    /**
     * 购物车总价
     */
    private BigDecimal cartTotalPrice;
}
