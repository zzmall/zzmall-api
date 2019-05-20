package com.zzmall.api.service;

import com.zzmall.api.common.vo.CartProductVO;
import com.zzmall.api.pojo.Cart;


/**
 * @Author Connor
 * @Date 2019/05/20 16:07
 */
public interface CartService {

    /**
     * 获取该用户的购物车信息
     *
     * @return
     */
    CartProductVO getCartByUserId(Integer userId);

    /**
     * 添加商品到购物车
     * @param cart
     * @return
     */
    CartProductVO add(Cart cart);

    /**
     * 更新购物车
     * @param cart
     * @return
     */
    CartProductVO update(Cart cart);

    /**
     * 按条件删除购物车中的商品
     *
     * @param userId
     * @param productIds
     * @return
     */
    CartProductVO delete(Integer userId, String[] productIds);

    /**
     * 选择/取消 商品
     * @param userId
     * @param productId
     * @param productChecked
     * @return
     */
    CartProductVO productChecked(Integer userId, Integer productId, Integer productChecked);

    /**
     * 查询在购物车里的产品数量
     * @param userId
     * @return
     */
    Integer getCartProductCount(Integer userId);

    /**
     * 选择/取消 全部商品
     * @param userId
     * @param checked
     * @return
     */
    CartProductVO productCheckedAll(Integer userId, Integer checked);
}
