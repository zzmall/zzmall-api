package com.zzmall.api.repositor;

import com.zzmall.api.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author Connor
 * @Date 2019/05/19 16:01
 */
public interface ProductRepository extends BaseRepository<Product, Integer> {

    Product getByCategoryId(Integer categoryId);

    Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable);
}
