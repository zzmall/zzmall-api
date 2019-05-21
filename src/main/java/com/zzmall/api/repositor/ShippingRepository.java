package com.zzmall.api.repositor;

import com.zzmall.api.pojo.Shipping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/21 19:01
 */
public interface ShippingRepository extends JpaRepository<Shipping, Integer> {

    List<Shipping> findAllByUserId(Integer userId);

    Page<Shipping> findAllByUserId(Pageable pageable, Integer userId);
}
