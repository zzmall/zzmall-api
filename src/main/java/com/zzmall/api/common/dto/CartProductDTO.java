package com.zzmall.api.common.dto;

import com.zzmall.api.common.constant.ApiConstant;
import com.zzmall.api.common.constant.CartProductConstant;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Connor
 * @Date 2019/05/20 16:18
 */
@Data
public class CartProductDTO {

    /**
     * 购物车id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品副标题
     */
    private String productSubtitle;

    /**
     * 商品小图
     */
    private String productMainImage;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品状态
     */
    private Integer productStatus;

    /**
     * 商品总价
     */
    private BigDecimal productTotalPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品是否被选中 / 商品选中状态
     */
    private Integer productChecked = CartProductConstant.CHECKED_TRUE;

    /**
     * 限制数量
     */
    private String limitQuantity = CartProductConstant.LIMIT_NUM_SUCCESS;


    /**
     * 计算商品总价
     *
     * @return
     */
    public BigDecimal getProductTotalPrice() {

        //总价 = 商品单价 * 商品数量
        BigDecimal total = this.productPrice.multiply(new BigDecimal(this.quantity));
        return this.productTotalPrice = total;
    }

    /**
     * 库存限制
     * @return
     */
    public String getLimitQuantity() {
        return this.limitQuantity =
                this.quantity > this.productStock ?
                        CartProductConstant.LIMIT_NUM_FAIL : CartProductConstant.LIMIT_NUM_SUCCESS;
    }



}
