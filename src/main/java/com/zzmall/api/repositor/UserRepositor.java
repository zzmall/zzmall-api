package com.zzmall.api.repositor;

import com.zzmall.api.pojo.User;

/**
 * @Author Connor
 * @Date 2019/05/18 16:39
 */
public interface UserRepositor extends BaseRepository<User, Integer> {

    User getUserByUsername(String username);

}
