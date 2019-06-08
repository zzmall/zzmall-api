package com.zzmall.api.common.vo;

import com.zzmall.api.common.type.ResponseCode;
import com.zzmall.api.common.type.ResponseMessage;
import lombok.Data;

/**
 * @Author Connor
 * @Date 2019/05/18 18:46
 * <p>
 * 返回给前端的对象
 */
@Data
public class ResponseVO<T> {

    private Integer status;

    private String msg;

    private T data;

    ResponseVO() {

    }

    ResponseVO(Integer status, String message) {
        this.status = status;
        this.msg = message;
    }

    ResponseVO(Integer status, String message, T data) {
        this.status = status;
        this.msg = message;
    }

    ResponseVO(ResponseCode status, T data) {
        this.status = status.getCode();
        this.data = data;
    }

    ResponseVO(ResponseCode status, ResponseMessage message) {
        this.status = status.getCode();
        this.msg = message.getMessage();
    }


    public static ResponseVO success() {
        return new ResponseVO(ResponseCode.SUCCESS, ResponseMessage.USER_CHECK);
    }

    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO<T>(ResponseCode.SUCCESS, data);
    }

    public static <T> ResponseVO<T> success(String message) {
        return new ResponseVO<T>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> ResponseVO<T> success(ResponseMessage message) {
        return new ResponseVO<T>(ResponseCode.SUCCESS, message);
    }

    public static ResponseVO success(Integer status, String message) {
        return new ResponseVO(status, message);
    }

    public static ResponseVO error(Integer status, String message) {
        return new ResponseVO(status, message);
    }

}
