package com.zzmall.api.util;

import org.springframework.util.DigestUtils;

/**
 * @Author Connor
 * @Date 2019/05/18 18:30
 * <p>
 * 加密
 */
public abstract class EncryptUtil {


    /**
     * MD5加密
     *
     * @param str 要加密的字符串
     * @return
     */
    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
    }
}
