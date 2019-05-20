package com.zzmall.api.service.impl;

import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.Product;
import com.zzmall.api.repositor.ProductRepository;
import com.zzmall.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author Connor
 * @Date 2019/05/19 17:13
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        try {
            return productRepository.findAll(pageable);
        } catch (Exception e) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_FAIL);
        }
    }

    @Override
    public Page<Product> findAllByCategoryId(Integer categoryId, Pageable pageable) {

        if (categoryId == null) {
            throw new ApiException(ResponseMessage.PRODUCT_ID_NULL);
        }

        Page<Product> productPage = productRepository.findAllByCategoryId(categoryId, pageable);

        if (productPage == null) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_ID_FAIL);
        }

        return productPage;

    }

    @Override
    public Product getProductById(Integer id) {

        if (id == null) {
            throw new ApiException(ResponseMessage.PRODUCT_ID_NULL);
        }

        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_ID_FAIL);
        }

        return product.get();
    }

    @Override
    public Product getProductByCategoryId(Integer id) {
        if (id == null) {
            throw new ApiException(ResponseMessage.PRODUCT_ID_NULL);
        }

        Product product = productRepository.getByCategoryId(id);

        if (product == null) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_ID_FAIL);
        }

        return product;
    }
}
