package com.zzmall.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        String str = "1";

        String[] strings = str.split(",");

        log.info("strings: {} , size: {}", strings, strings.length);

    }

}
