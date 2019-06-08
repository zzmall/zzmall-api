package com.zzmall.api.common.type;

import lombok.Getter;

/**
 * @Author Connor
 * @Date 2019/05/18 19:12
 * <p>
 * 返回给前端的状态码
 */
@Getter
public enum ResponseCode implements BaseResponse {

    SUCCESS(0),
    FAIL(1),
    NOT_LOGIN(10)
    ;


    private Integer code;

//


    ResponseCode(Integer code) {
        this.code = code;
    }


    @Override
    public String getMessage() {
        return null;
    }
}
