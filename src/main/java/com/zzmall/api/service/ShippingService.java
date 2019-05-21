package com.zzmall.api.service;

import com.zzmall.api.pojo.Shipping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author Connor
 * @Date 2019/05/21 19:02
 */
public interface ShippingService {


    /**
     * 新增收货地址
     *
     * @param shipping
     * @return
     */
    Integer add(Shipping shipping);


    /**
     * 按条件删除收货地址
     *
     * @param id
     * @param userId
     */
    void delete(Integer id, Integer userId);

    /**
     * 更新收货地址
     *
     * @param shipping
     */
    void update(Shipping shipping);

    /**
     * 按条件获取收货地址信息
     *
     * @param id
     * @param userId
     * @return
     */
    Shipping getShippingById(Integer id, Integer userId);

    /**
     * 分页获取该用户所有收货地址
     *
     * @param pageable
     * @param userId
     * @return
     */
    Page<Shipping> list(Pageable pageable, Integer userId);

}
