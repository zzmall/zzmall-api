package com.zzmall.api.common.form;

import com.zzmall.api.common.constant.ApiConstant;
import lombok.Data;

/**
 * @Author Connor
 * @Date 2019/05/18 22:50
 *
 * 用户注册表单
 */
@Data
public class RegisterForm {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String question;
    private String answer;
    private Integer role = ApiConstant.GENERAL_USER;

}
