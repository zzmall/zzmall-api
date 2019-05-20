package com.zzmall.api.common.constant;

/**
 * @Author Connor
 * @Date 2019/05/20 20:48
 */
public interface CartProductConstant {

    /**
     * 商品被选中
     */
    Integer CHECKED_TRUE = 1;

    /**
     * 商品没有被选中
     */
    Integer CHECKED_FALSE = 0;

    /**
     * 商品数量没有超过库存的标识
     */
    String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";

    /**
     * 商品数量超过库存的标识
     */
    String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
}
