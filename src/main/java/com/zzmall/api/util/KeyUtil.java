package com.zzmall.api.util;

import java.util.Random;

/**
 * @Author Connor
 * @Date 2019/05/23 1:16
 */
public abstract class KeyUtil {


    /**
     * 生成唯一主键
     * 时间戳
     * @return
     */
    public static synchronized Long genUniqueKey() {
//        Random random = new Random();
//        Integer i = random.nextInt(90000) + 10000;
        return System.currentTimeMillis();

    }
}
