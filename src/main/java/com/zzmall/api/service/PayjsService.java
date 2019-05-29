package com.zzmall.api.service;

import com.zzmall.api.common.dto.PayCheckDTO;
import com.zzmall.api.common.dto.NativeRequestDTO;
import com.zzmall.api.common.dto.NativeResponseDTO;
import com.zzmall.api.common.dto.PayCheckResponseDTO;

/**
 * @Author Connor
 * @Date 2019/05/28 16:16
 */
public interface PayjsService {

    /**
     * 扫码支付
     *
     * @param requestDTO
     * @return
     */
    NativeResponseDTO nativePay(NativeRequestDTO requestDTO);

    /**
     * 订单查询
     */
    PayCheckResponseDTO check(PayCheckDTO checkDTO);
}
