package com.zzmall.api.repositor;

import com.zzmall.api.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/22 20:00
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {


    List<Order> findAllByUserId(Integer userId);

}
