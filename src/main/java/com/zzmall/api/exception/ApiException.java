package com.zzmall.api.exception;

import com.zzmall.api.common.type.ResponseCode;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.common.vo.ResponseVO;
import lombok.Data;
import lombok.Getter;

/**
 * @Author Connor
 * @Date 2019/05/17 8:42
 *
 * 全局异常类
 */
@Data
public class ApiException extends RuntimeException {

    private Integer status;

    public ApiException() {
        super();
        this.status = ResponseCode.FAIL.getCode();
    }

    public ApiException(String message) {
        super(message);
        this.status = ResponseCode.FAIL.getCode();
    }

    public ApiException(ResponseMessage message) {
        super(message.getMessage());
        this.status = ResponseCode.FAIL.getCode();
    }

    public ApiException(Integer status, String message) {
        super(message);
        this.status = status;
    }
}
