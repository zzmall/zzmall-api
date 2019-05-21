package com.zzmall.api.service.impl;

import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.Shipping;
import com.zzmall.api.repositor.ShippingRepository;
import com.zzmall.api.service.ShippingService;
import com.zzmall.api.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Connor
 * @Date 2019/05/21 19:11
 */
@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    private ShippingRepository shippingRepository;

    @Override
    public Integer add(Shipping shipping) {

        if (!Optional.ofNullable(shipping).isPresent()) {
            throw new ApiException(ResponseMessage.FAIL);
        }
        Shipping result = shippingRepository.save(shipping);
        if (result == null) {
            throw new ApiException(ResponseMessage.SHIPPING_ADD_FAIL);
        }
        return result.getId();
    }

    @Override
    public void delete(Integer id, Integer userId) {
        if (id == null | userId == null) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_FAIL);
        }

        List<Shipping> shippingList = shippingRepository.findAllByUserId(userId);
        Optional<Shipping> shipping = shippingList.stream().filter(i -> i.getId() == id).findFirst();
        if (!shipping.isPresent()) {
            throw new ApiException(ResponseMessage.SHIPPING_ID_NULL);
        }
        //删除
        try {
            shippingRepository.delete(shipping.get());
        } catch (Exception e) {
            throw new ApiException(ResponseMessage.SHIPPING_DELETE_FAIL);
        }
    }

    @Override
    public void update(Shipping shipping) {
        if (!Optional.ofNullable(shipping).isPresent() | shipping.getId() == null) {
            throw new ApiException(ResponseMessage.FAIL);
        }
        //获取该用户的收货地址列表
        List<Shipping> shippingList = shippingRepository.findAllByUserId(shipping.getUserId());
        //找出对应id的收货地址
        Optional<Shipping> optional = shippingList.stream().filter(i -> i.getId() == shipping.getId()).findFirst();
        if (!optional.isPresent()) {
            throw new ApiException(ResponseMessage.SHIPPING_ID_NULL);
        }
        //将修改的数据进行填充
        BeanUtils.copyProperties(shipping, optional.get());
        //保存修改
        Shipping result = shippingRepository.save(optional.get());
        if (result == null) {
            throw new ApiException(ResponseMessage.SHIPPING_UPDATE_FAIL);
        }
    }

    @Override
    public Shipping getShippingById(Integer id, Integer userId) {
        if (id == null | userId == null) {
            throw new ApiException(ResponseMessage.PRODUCT_SELECT_FAIL);
        }

        List<Shipping> shippingList = shippingRepository.findAllByUserId(userId);
        Optional<Shipping> shipping = shippingList.stream().filter(i -> i.getId() == id).findFirst();
        if (!shipping.isPresent()) {
            throw new ApiException(ResponseMessage.SHIPPING_ID_NULL);
        }
        return shipping.get();
    }

    @Override
    public Page<Shipping> list(Pageable pageable, Integer userId) {
        if (userId == null) {
            throw new ApiException(ResponseMessage.FAIL);
        }
        Page<Shipping> shippingPage = shippingRepository.findAllByUserId(pageable, userId);
        return shippingPage;
    }
}
