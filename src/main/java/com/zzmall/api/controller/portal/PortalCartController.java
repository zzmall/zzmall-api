package com.zzmall.api.controller.portal;

import com.zzmall.api.common.constant.CartProductConstant;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.CartProductVO;
import com.zzmall.api.common.vo.ResponseVO;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.Cart;
import com.zzmall.api.pojo.User;
import com.zzmall.api.service.CartService;
import com.zzmall.api.util.SecurityUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Connor
 * @Date 2019/05/20 19:40
 */
@RestController
@RequestMapping("/cart")
public class PortalCartController {

    @Autowired
    private CartService cartService;

    /**
     * 获取登陆用户的购物车列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/list.do")
    public ResponseVO<CartProductVO> list(HttpServletRequest request) {
        User user = SecurityUtil.isLogin(request.getSession());
        CartProductVO cartProductVO = cartService.getCartByUserId(user.getId());
        return ResponseVO.success(cartProductVO);
    }

    /**
     * 购物车添加商品
     *
     * @param productId
     * @param count
     * @param request
     * @return
     */
    @RequestMapping("/add.do")
    public ResponseVO<CartProductVO> add(Integer productId, Integer count, HttpServletRequest request) {

        User user = SecurityUtil.isLogin(request.getSession());

        if (productId == null) {
            throw new ApiException(ResponseMessage.PRODUCT_ID_NULL);
        }

        if (count == null) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_FAIL);
        }

        Cart cart = new Cart(user.getId(), productId, count);
        CartProductVO result = cartService.add(cart);
        return ResponseVO.success(result);
    }

    /**
     * 更新购物车某个产品数量
     *
     * @param productId
     * @param count
     * @param request
     * @return
     */
    @RequestMapping("/update.do")
    public ResponseVO<CartProductVO> update(Integer productId, Integer count, HttpServletRequest request) {

        User user = SecurityUtil.isLogin(request.getSession());

        if (productId == null) {
            throw new ApiException(ResponseMessage.PRODUCT_ID_NULL);
        }

        if (count == null) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_FAIL);
        }

        Cart cart = new Cart(user.getId(), productId, count);
        CartProductVO result = cartService.update(cart);
        return ResponseVO.success(result);
    }

    /**
     * 移除购物车某个产品
     *
     * @param productIds
     * @param request
     * @return
     */
    @RequestMapping("/delete_product.do")
    public ResponseVO<CartProductVO> delete(String productIds, HttpServletRequest request) {
        User user = SecurityUtil.isLogin(request.getSession());

        if (StringUtil.isNullOrEmpty(productIds)) {
            throw new ApiException(ResponseMessage.PRODUCT_ID_NULL);
        }

        //分割
        String[] strings = productIds.split(",");

        //删除
        CartProductVO cartProductVO = cartService.delete(user.getId(), strings);
        return ResponseVO.success(cartProductVO);
    }

    /**
     * 购物车选中某个商品
     *
     * @param productId
     * @param request
     * @return
     */
    @RequestMapping("/select.do")
    public ResponseVO<CartProductVO> select(Integer productId, HttpServletRequest request) {

        if (productId == null) {
            throw new ApiException(ResponseMessage.PRODUCT_ID_NULL);
        }

        User user = SecurityUtil.isLogin(request.getSession());

        CartProductVO cartProductVO = cartService.productChecked(
                user.getId(), productId, CartProductConstant.CHECKED_TRUE
        );
        return ResponseVO.success(cartProductVO);
    }

    /**
     * 购物车取消选中某个商品
     *
     * @param productId
     * @param request
     * @return
     */
    @RequestMapping("/un_select.do")
    public ResponseVO<CartProductVO> unSelect(Integer productId, HttpServletRequest request) {

        if (productId == null) {
            throw new ApiException(ResponseMessage.PRODUCT_ID_NULL);
        }

        User user = SecurityUtil.isLogin(request.getSession());

        CartProductVO cartProductVO = cartService.productChecked(
                user.getId(), productId, CartProductConstant.CHECKED_FALSE
        );
        return ResponseVO.success(cartProductVO);
    }

    /**
     * 查询在购物车里的产品数量
     *
     * @param request
     * @return
     */
    @RequestMapping("/get_cart_product_count.do")
    public ResponseVO<Integer> getCartProductCount(HttpServletRequest request) {

        User user = null;

        try {
            user = SecurityUtil.isLogin(request.getSession());
        } catch (ApiException e) {
            return ResponseVO.success(0);
        }

        Integer count = cartService.getCartProductCount(user.getId());

        return ResponseVO.success(count);
    }

    /**
     * 购物车全选
     *
     * @param request
     * @return
     */
    @RequestMapping("/select_all.do")
    public ResponseVO<CartProductVO> selectAll(HttpServletRequest request) {
        User user = SecurityUtil.isLogin(request.getSession());

        CartProductVO cartProductVO = cartService.productCheckedAll(
                user.getId(), CartProductConstant.CHECKED_TRUE
        );
        return ResponseVO.success(cartProductVO);
    }

    /**
     * 购物车取消全选
     *
     * @param request
     * @return
     */
    @RequestMapping("/un_select_all.do")
    public ResponseVO<CartProductVO> unSelectAll(HttpServletRequest request) {
        User user = SecurityUtil.isLogin(request.getSession());

        CartProductVO cartProductVO = cartService.productCheckedAll(
                user.getId(), CartProductConstant.CHECKED_FALSE
        );
        return ResponseVO.success(cartProductVO);
    }

}
