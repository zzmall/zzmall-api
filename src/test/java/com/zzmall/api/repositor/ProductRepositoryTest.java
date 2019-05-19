package com.zzmall.api.repositor;

import com.zzmall.api.common.type.ProductSortType;
import com.zzmall.api.pojo.Product;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author Connor
 * @Date 2019/05/19 16:07
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAll() {

        Page<Product> page = productRepository.findAllByCategoryId(
                100002,
                PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "price"))
        );

        page.getContent().forEach(i -> System.out.println(i.getName()));

    }

    @Test
    public void test() {

        Product product = productRepository.getByCategoryId(100002);

        Assert.assertNotNull(product);

    }
}