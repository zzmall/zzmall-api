package com.zzmall.api.repositor;

import com.zzmall.api.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/22 20:01
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findAllByUserId(Integer userId);

    List<OrderItem> findAllByOrderNoAndUserId(BigInteger orderNo, Integer userId);


}
