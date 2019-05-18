package com.zzmall.api.common.form;

import lombok.Data;

/**
 * @Author Connor
 * @Date 2019/05/19 0:28
 */
@Data
public class UserInfoForm {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 密码提示问题
     */
    private String question;

    /**
     * 密码提示问题答案
     */
    private String answer;



}
