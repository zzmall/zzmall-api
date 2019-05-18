package com.zzmall.api.service;

import com.zzmall.api.pojo.User;

import java.util.List;

/**
 * @Author Connor
 * @Date 2019/05/18 20:30
 */
public interface UserService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回该用户信息
     */
    User login(String username, String password);

    /**
     * 用户注册
     *
     * @param user 用户信息
     */
    void register(User user);

    /**
     * 通过id查询用户信息
     *
     * @param id 用户id
     * @return 返回该用户信息
     */
    User getUserById(Integer id);

    /**
     * 通过username查询用户信息
     *
     * @param username 用户名
     * @return 返回该用户信息
     */
    User getUserByUsername(String username);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     */
    void update(User user);

    /**
     * 获取所有用户信息
     * @return
     */
    List<User> list();


}
