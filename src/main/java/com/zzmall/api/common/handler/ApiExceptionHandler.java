package com.zzmall.api.common.handler;

import com.zzmall.api.common.vo.ResponseVO;
import com.zzmall.api.exception.ApiException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Connor
 * @Date 2019/05/17 8:46
 *
 * 统一异常处理
 */
@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResponseVO handlerApiException(ApiException e) {
        return ResponseVO.error(e.getStatus(), e.getMessage());
    }

}
