package com.zzmall.api.service;

import com.zzmall.api.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author Connor
 * @Date 2019/05/19 16:44
 */
public interface ProductService {

    /**
     * 分页查询所有商品
     *
     * @param pageable
     * @return
     */
    Page<Product> findAll(Pageable pageable);

    /**
     * 按条件分页查询所有商品
     *
     * @param categoryId
     * @param pageable
     * @return
     */
    Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);

    /**
     * 按指定id查询商品信息
     *
     * @param id productId
     * @return
     */
    Product getProductById(Integer id);

    /**
     * 按指定id查询商品信息
     *
     * @param id categoryId
     * @return
     */
    Product getProductByCategoryId(Integer id);
}
