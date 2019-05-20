package com.zzmall.api.service.impl;

import com.zzmall.api.common.dto.CartProductDTO;
import com.zzmall.api.common.vo.CartProductVO;
import com.zzmall.api.pojo.Cart;
import com.zzmall.api.pojo.Product;
import com.zzmall.api.repositor.CartRepository;
import com.zzmall.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @Author Connor
 * @Date 2019/05/20 16:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CartServiceImplTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartServiceImpl cartService;

    @Test
    public void test() {

        Product product = productService.getProductById(26);


        CartProductDTO cartProductDTO = new CartProductDTO();

        cartProductDTO.setQuantity(3);
        cartProductDTO.setProductPrice(product.getPrice());

        log.info("商品总价为：{}", cartProductDTO.getProductTotalPrice());
    }

    @Test
    public void test2() {

        List<Cart> cartList = cartRepository.findAllByUserId(21);

        Assert.assertNotNull(cartList);

    }

    @Test
    public void list() {

//        List<CartProductVO> list = cartService.list(21);
////
////        Assert.assertNotNull(list);
////
////        log.info("购物车：{}", list);

    }




}