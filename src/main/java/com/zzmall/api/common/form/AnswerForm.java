package com.zzmall.api.common.form;

import lombok.Data;

/**
 * @Author Connor
 * @Date 2019/05/18 23:50
 */
@Data
public class AnswerForm {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码提示问题
     */
    private String question;

    /**
     * 密码提示问题答案
     */
    private String answer;
}
