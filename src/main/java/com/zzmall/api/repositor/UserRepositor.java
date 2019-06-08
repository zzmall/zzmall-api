package com.zzmall.api.repositor;

import com.zzmall.api.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Connor
 * @Date 2019/05/18 16:39
 */
public interface UserRepositor extends JpaRepository<User, Integer> {

    User getUserByUsername(String username);

}
