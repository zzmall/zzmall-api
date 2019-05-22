package com.zzmall.api.repositor;

import com.zzmall.api.pojo.PayInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/23 0:09
 */
public interface PayInfoRepository extends JpaRepository<PayInfo, Integer> {


    List<PayInfo> findAllByUserId(Integer userId);
}
