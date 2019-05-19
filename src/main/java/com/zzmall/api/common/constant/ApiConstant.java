package com.zzmall.api.common.constant;

/**
 * @Author Connor
 * @Date 2019/05/19 0:34
 */
public interface ApiConstant {

    /**
     * 登录标识
     */
    String LOGIN_PREFIX = "user";

    /**
     * 普通用户标识
     */
    Integer GENERAL_USER = 0;

    /**
     * 管理员标识
     */
    Integer ADMIN_USER = 1;

    /**
     * 默认当前页
     */
    Integer DEFAULT_PAGE = 1;

    /**
     * 默认每页的大小
     */
    Integer DEFAULT_PAGE_SIZE = 10;
}
