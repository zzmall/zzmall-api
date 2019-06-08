package com.zzmall.api.common.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author Connor
 * @Date 2019/06/05 12:10
 * <p>
 * 登陆表单
 */
@Data
public class LoginForm {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
