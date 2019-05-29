package com.zzmall.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.zzmall.api.common.dto.PayCheckDTO;
import com.zzmall.api.common.dto.NativeRequestDTO;
import com.zzmall.api.common.dto.NativeResponseDTO;
import com.zzmall.api.common.dto.PayCheckResponseDTO;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.service.PayjsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Connor
 * @Date 2019/05/28 16:21
 */
@Service
@Slf4j
public class PayjsServiceImpl implements PayjsService {

    private final String NATIVE_URL = "https://payjs.cn/api/native";
    private final String CHECK_URL = "https://payjs.cn/api/check";

    @Override
    public NativeResponseDTO nativePay(NativeRequestDTO requestDTO) {

        Map<String, String> param = new HashMap<>();

        param.put("body", requestDTO.getBody());
        param.put("mchid", requestDTO.getMchid());
        param.put("out_trade_no", requestDTO.getOut_trade_no());
        param.put("sign", requestDTO.getSign());
        param.put("total_fee", requestDTO.getTotal_fee().toString());
//        param.put("key", "8gLwNB1O1oSL4n5i");

        HttpRequest httpRequest = HttpRequest.get(NATIVE_URL, param, true);
        log.info("httpRequest:{}", httpRequest.toString());
        JSONObject jsonObject = JSONObject.parseObject(httpRequest.body());
        log.info("body:{}", jsonObject.toString());
        NativeResponseDTO responseDTO = JSON.parseObject(jsonObject.toJSONString(), NativeResponseDTO.class);

        if (responseDTO.getReturn_code() == 0) {
            throw new ApiException(jsonObject.get("return_msg").toString());
        }

        return responseDTO;
    }

    @Override
    public PayCheckResponseDTO check(PayCheckDTO checkDTO) {

        Map<String, String> param = new HashMap<>();

        param.put("payjs_order_id", checkDTO.getPayjs_order_id());
        param.put("sign", checkDTO.getSign());

        String body = HttpRequest.get(CHECK_URL, param, true).body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        PayCheckResponseDTO responseDTO = JSON.parseObject(jsonObject.toJSONString(), PayCheckResponseDTO.class);

        if (responseDTO.getReturn_code() == 0) {
            throw new ApiException(ResponseMessage.FAIL);
        }

        return responseDTO;
    }
}
