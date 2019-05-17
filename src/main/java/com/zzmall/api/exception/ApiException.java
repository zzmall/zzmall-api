package com.zzmall.api.exception;

/**
 * @Author Connor
 * @Date 2019/05/17 8:42
 *
 * 全局异常类
 */
public class ApiException extends RuntimeException {


    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    protected ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
