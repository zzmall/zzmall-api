package com.zzmall.api.common.type;

import lombok.Getter;

/**
 * @Author Connor
 * @Date 2019/05/18 19:21
 * <p>
 * 返回给前端的提示信息
 */
@Getter
public enum ResponseMessage {

    FAIL("服务器异常"),

    USER_LOGIN_SUCCESS("登陆成功"),
    USER_LOGIN_FAIL("登陆失败"),
    USER_NOT_LOGIN("用户未登录,无法获取当前用户信息"),
    USER_USERNAME_NULL("用户名不能为空"),
    USER_PASSWORD_NULL("密码不能为空"),
    USER_PASSWORD_FAIL("密码错误"),

    USER_PASSWORD_OLD_FAIL("旧密码错误"),

    USER_UPDATE_INFO_SUCCESS("更新个人信息成功"),
    USER_UPDATE_INFO_FAIL("更新个人信息失败"),

    USER_REGISTER_FAIL("用户已存在"),

    USER_LOGOUT_SUCCESS("退出成功"),

    USER_QUESTION_FAIL("该用户未设置找回密码问题"),
    USER_ANSWER_FAIL("问题答案错误"),

    USER_PASSWORD_UPDATE_SUCCESS("修改密码成功"),
    USER_PASSWORD_UPDATE_FAIL("修改密码失败"),

    TOKEN_FAIL("token已经失效"),

    USER_FAIL("用户不存在"),

    USER_CHECK("校验成功")
    ;


    private String message;

    ResponseMessage(String message) {
        this.message = message;
    }
}
