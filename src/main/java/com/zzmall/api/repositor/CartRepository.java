package com.zzmall.api.repositor;

import com.zzmall.api.pojo.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/20 16:05
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {


    List<Cart> findAllByUserId(Integer userId);


}
