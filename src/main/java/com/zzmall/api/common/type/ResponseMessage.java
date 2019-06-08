package com.zzmall.api.common.type;

import lombok.Getter;

/**
 * @Author Connor
 * @Date 2019/05/18 19:21
 * <p>
 * 返回给前端的提示信息
 */
@Getter
public enum ResponseMessage implements BaseResponse {

    FAIL("服务器异常"),

    PRODUCT_SELECT_FAIL("参数错误"),
    PRODUCT_ID_NULL("商品不存在"),
    PRODUCT_SELECT_ID_FAIL("该商品已下架或已删除"),

    ORDER_CREATE_FAIL("创建订单失败"),
    ORDER_ID_NULL("找不到此订单"),
    ORDER_PAY_REPEAT_FAIL("此订单已付款，请勿重复付款"),
    ORDER_PAY_FAIL("订单支付失败"),
    ORDER_PAY_SUCCESS("订单支付成功"),
    ORDER_CANCEL_PAY_FAIL("此订单已付款，无法被取消"),

    SHIPPING_ID_NULL("该收货地址不存在或已删除"),
    SHIPPING_ADD_FAIL("新建地址失败"),
    SHIPPING_ADD_SUCCESS("新建地址成功"),
    SHIPPING_UPDATE_FAIL("更新地址失败"),
    SHIPPING_UPDATE_SUCCESS("更新地址成功"),
    SHIPPING_DELETE_FAIL("删除地址失败"),
    SHIPPING_DELETE_SUCCESS("删除地址成功"),

    CART_NULL("购物车为空"),
    CART_PRODUCT_ADD_FAIL("商品添加失败"),
    CART_PRODUCT_UPDATE_FAIL("商品更新失败"),
    CART_PRODUCT_ADD_REPEAT_FAIL("商品重复"),
    CART_PRODUCT_UPDATE_NULL("您的购物车中没有该商品"),

    USER_LOGIN_ID_FAIL("传入的用户id跟当前登陆的用户不符"),
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


    @Override
    public Integer getCode() {
        return null;
    }
}
