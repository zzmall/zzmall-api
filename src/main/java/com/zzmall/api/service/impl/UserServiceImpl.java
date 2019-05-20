package com.zzmall.api.service.impl;

import com.zzmall.api.common.type.ResponseMessage;
import com.zzmall.api.exception.ApiException;
import com.zzmall.api.pojo.User;
import com.zzmall.api.repositor.UserRepositor;
import com.zzmall.api.service.UserService;
import com.zzmall.api.util.EncryptUtil;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Connor
 * @Date 2019/05/18 20:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositor userRepositor;

    @Override
    public User login(String username, String password) {
        if (StringUtil.isNullOrEmpty(username)) {
            throw new ApiException(ResponseMessage.USER_USERNAME_NULL);
        }
        if (StringUtil.isNullOrEmpty(password)) {
            throw new ApiException(ResponseMessage.USER_PASSWORD_FAIL);
        }

        User user = userRepositor.getUserByUsername(username);

        if (user == null) {
            throw new ApiException(ResponseMessage.USER_PASSWORD_FAIL);
        }

        if (!user.getPassword().equals(EncryptUtil.md5(password))) {
            throw new ApiException(ResponseMessage.USER_PASSWORD_FAIL);
        }

        //最后把password设置为空
        user.setPassword(StringUtil.EMPTY_STRING);

        return user;
    }

    @Override
    public void register(User user) {
        if (user == null) {
            throw new ApiException();
        }

        //检查数据库中是否存在该用户名
        User temp = userRepositor.getUserByUsername(user.getUsername());
        if (temp != null) {
            throw new ApiException(ResponseMessage.USER_REGISTER_FAIL);
        }

        //加密
        user.setPassword(EncryptUtil.md5(user.getPassword()));

        //注册
        if (userRepositor.save(user) == null) throw new ApiException(ResponseMessage.USER_REGISTER_FAIL);
    }

    @Override
    public User getUserById(Integer id) {
        if (id == null) {
            throw new ApiException(ResponseMessage.USER_FAIL);
        }

        Optional<User> user = userRepositor.findById(id);
        if (!user.isPresent()) {
            throw new ApiException(ResponseMessage.USER_FAIL);
        }
        return user.get();
    }

    @Override
    public User getUserByUsername(String username) {

        User user = userRepositor.getUserByUsername(username);

        if (user == null) {
            throw new ApiException(ResponseMessage.USER_FAIL);
        }

        return user;
    }

    @Override
    public void update(User user) {
        if (user == null | user.getId() == null) {
            throw new ApiException(ResponseMessage.USER_FAIL);
        }

        //加密
        user.setPassword(EncryptUtil.md5(user.getPassword()));

        //保存修改
        if (userRepositor.save(user) == null) throw new ApiException(ResponseMessage.USER_PASSWORD_UPDATE_FAIL);
    }

    @Override
    public List<User> list() {

        List<User> list = userRepositor.findAll();
        if (list == null) {
            throw new ApiException(ResponseMessage.FAIL);
        }
        return list;
    }
}
