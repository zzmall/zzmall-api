package com.zzmall.api.repositor;

import com.zzmall.api.pojo.OrderItem;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/22 20:01
 */
public interface OrderItemRepository extends BaseRepository<OrderItem, Integer> {

    List<OrderItem> findAllByOrderNoAndUserId(BigInteger orderNo, Integer userId);

}
