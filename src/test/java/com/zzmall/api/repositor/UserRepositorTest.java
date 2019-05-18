package com.zzmall.api.repositor;

import com.zzmall.api.pojo.User;
import com.zzmall.api.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Author Connor
 * @Date 2019/05/18 16:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositorTest {


    @Autowired
    private UserRepositor userRepositor;

    @Test
    public void test() {

        User result = userRepositor.getUserByUsername("admin");
        Assert.assertEquals("admin", result.getUsername());



    }

    @Test
    public void save() {

        User user = new User();

        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("xxxx@zzmall.com");
        user.setPhone("18888888888");
        user.setQuestion("问题");
        user.setAnswer("答案");
        user.setRole(0);

        User result = userRepositor.save(user);
        Assert.assertEquals("test", result.getUsername());
    }

    @Test
    public void update() {

        User user = userRepositor.getOne(22);
        user.setPassword(EncryptUtil.md5("admin"));

        User result = userRepositor.save(user);
        Assert.assertEquals("22", result.getId().toString());

    }

}