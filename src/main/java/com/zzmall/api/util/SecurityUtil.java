package com.zzmall.api.util;

import com.zzmall.api.common.constant.ApiConstant;
import com.zzmall.api.common.type.ResponseCode;
import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * @Author Connor
 * @Date 2019/05/20 22:02
 */
public class SecurityUtil {

    /**
     * 校验用户是否登陆
     *
     * @param session
     * @return
     */
    public static User isLogin(HttpSession session) {
        User user = (User) session.getAttribute(ApiConstant.LOGIN_PREFIX);
        if (user == null) {
            throw new ApiException(ResponseCode.NOT_LOGIN.getCode(), ResponseMessage.USER_NOT_LOGIN.getMessage());
        }
        return user;
    }
}
