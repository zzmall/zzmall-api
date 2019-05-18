package com.zzmall.api.common.form;

import lombok.Data;

/**
 * @Author Connor
 * @Date 2019/05/19 0:02
 */
@Data
public class ResetPasswordForm {

    /**
     * 用户名
     */
    private String username;

    /**
     * 新密码
     */
    private String passwordNew;

    /**
     * token
     */
    private String forgetToken;
}
