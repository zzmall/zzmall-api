package com.zzmall.api.service.impl;

import com.zzmall.api.common.constant.CartProductConstant;
import com.zzmall.api.common.dto.CartProductDTO;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.CartProductVO;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.Cart;
import com.zzmall.api.pojo.Product;
import com.zzmall.api.repositor.CartRepository;
import com.zzmall.api.service.CartService;
import com.zzmall.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/20 16:15
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Override
    public CartProductVO getCartByUserId(Integer userId) {

        List<CartProductDTO> cartProductDTOList = new ArrayList<>();
        CartProductDTO cartProductDTO;
        CartProductVO cartProductVO = new CartProductVO();
        List<Cart> cartList = cartRepository.findAllByUserId(userId);

        if (cartList == null) {
            throw new ApiException(ResponseMessage.CART_NULL);
        }

        //购物车不为空
        for (Cart cart : cartList) {
            Product product = productService.getProductById(cart.getProductId());

            cartProductDTO = new CartProductDTO();
            cartProductDTO.setId(cart.getId());
            cartProductDTO.setQuantity(cart.getQuantity());
            cartProductDTO.setProductChecked(cart.getChecked());
            cartProductDTO.setProductId(product.getId());
            cartProductDTO.setUserId(userId);
            cartProductDTO.setProductName(product.getName());
            cartProductDTO.setProductSubtitle(product.getSubtitle());
            cartProductDTO.setProductMainImage(product.getMainImage());
            cartProductDTO.setProductPrice(product.getPrice());
            cartProductDTO.setProductStatus(product.getStatus());
            cartProductDTO.setProductStock(product.getStock());
            cartProductDTOList.add(cartProductDTO);
        }

        //计算总价
        //注意：只计算购物车中被选中的商品
        BigDecimal totalPrice = cartProductDTOList.stream()
                .filter(i -> i.getProductChecked() == CartProductConstant.CHECKED_TRUE)
                .map(CartProductDTO::getProductTotalPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        long count = cartProductDTOList.stream()
                .filter(i -> i.getProductChecked() == CartProductConstant.CHECKED_TRUE)
                .count();

        boolean allChecked = count == cartProductDTOList.size() ? Boolean.TRUE : Boolean.FALSE;

        cartProductVO.setAllChecked(allChecked);
        cartProductVO.setProductList(cartProductDTOList);
        cartProductVO.setCartTotalPrice(totalPrice);

        return cartProductVO;
    }

    @Override
    public CartProductVO add(Cart cart) {
        if (cart == null) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_FAIL);
        }

        //判断商品是否已经存在于该用户的购物车中
        if (isUserCartProduct(cart.getProductId(), cart.getUserId())) {
            throw new ApiException(ResponseMessage.CART_PRODUCT_ADD_REPEAT_FAIL);
        }

        //添加到购物车
        Cart result = cartRepository.save(cart);
        if (result == null) {
            throw new ApiException(ResponseMessage.CART_PRODUCT_ADD_FAIL);
        }

        return getCartByUserId(result.getUserId());
    }

    @Override
    public CartProductVO update(Cart cart) {

        if (cart == null) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_FAIL);
        }

        //判断商品是否已经存在于该用户的购物车中
        if (!isUserCartProduct(cart.getProductId(), cart.getUserId())) {
            throw new ApiException(ResponseMessage.CART_PRODUCT_UPDATE_NULL);
        }

        //获取该用户的购物车
        List<Cart> cartList = cartRepository.findAllByUserId(cart.getUserId());

        //获取该购物车的id
        Integer cartId = cartList.stream()
                .filter(i -> i.getProductId() == cart.getProductId())
                .findFirst()
                .get()
                .getId();

        cart.setId(cartId);
        if (cartRepository.save(cart) == null) throw new ApiException(ResponseMessage.CART_PRODUCT_UPDATE_FAIL);
        return getCartByUserId(cart.getUserId());
    }

    @Override
    public CartProductVO delete(Integer userId, String[] productIds) {

        List<Cart> cartList = cartRepository.findAllByUserId(userId);

        for (String productId : productIds) {

            //1.判断该商品是否存在于该用户的购物车中
            if (!isUserCartProduct(Integer.parseInt(productId), userId)) {
                throw new ApiException(ResponseMessage.CART_PRODUCT_UPDATE_NULL);
            }
            //2.从该用户的购物车中取出
            Cart cart = cartList.stream().filter(i -> i.getProductId() == Integer.parseInt(productId)).findFirst().get();
            //3.删除
            cartRepository.delete(cart);
        }
        return getCartByUserId(userId);
    }

    @Override
    public CartProductVO productChecked(Integer userId, Integer productId, Integer productChecked) {

        if (!isUserCartProduct(productId, userId)) {
            throw new ApiException(ResponseMessage.CART_PRODUCT_UPDATE_NULL);
        }

        List<Cart> cartList = cartRepository.findAllByUserId(userId);
        Cart cart = cartList.stream().filter(i -> i.getProductId() == productId).findFirst().get();
        cart.setChecked(productChecked);
        //保存修改
        cartRepository.save(cart);
        return getCartByUserId(userId);
    }

    @Override
    public Integer getCartProductCount(Integer userId) {
        if (userId == null) {
            throw new ApiException(ResponseMessage.FAIL);
        }

        List<Cart> cartList = cartRepository.findAllByUserId(userId);
        return cartList.size();
    }

    @Override
    public CartProductVO productCheckedAll(Integer userId, Integer checked) {

        if (userId == null) {
            throw new ApiException(ResponseMessage.FAIL);
        }

        List<Cart> cartList = cartRepository.findAllByUserId(userId);
        cartList.forEach(i -> i.setChecked(checked));

        //保存所有
        try {
            cartRepository.saveAll(cartList);
        } catch (Exception e) {
            throw new ApiException(ResponseMessage.CART_PRODUCT_UPDATE_FAIL);
        }

        return getCartByUserId(userId);
    }

    /**
     * 校验该商品是否存在于该用户的购物车中
     *
     * @param productId
     * @param userId
     * @return
     */
    private boolean isUserCartProduct(Integer productId, Integer userId) {
        //判断该商品是否存在
        productService.getProductById(productId);

        //获取该用户的购物车信息
        List<Cart> cartList = cartRepository.findAllByUserId(userId);

        //判断该商品是否已经存在于该用户的购物车中
        long count = cartList.stream().filter(i -> i.getProductId() == productId).count();

        return count > 0 ? true : false;
    }


}
