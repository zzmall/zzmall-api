package com.zzmall.api.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;


import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @Author Connor
 * @Date 2019/05/23 1:16
 */
@Slf4j
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

    /**
     * 获取签名
     * @param source 需要签名的数据
     * @param key 签名用到的key
     * @return 数据签名
     */
    public static String getSign(Object source, String key) {

        //将序列化的字符串转换为JSONObject
        JSONObject jsonObject = JSONObject.parseObject(serialize(source));

        Set<String> keys = jsonObject.keySet();
        List<String> keysList = new ArrayList<>(keys.size());
        keys.forEach(i -> keysList.add(i));
        //排序
        Collections.sort(keysList);

        log.info("keysList:{}", keysList);

        StringBuilder stringBuilder = new StringBuilder();
        keysList.forEach(i -> {
            stringBuilder.append(i + "=" + jsonObject.get(i) + "&");
        });
        stringBuilder.append("key=" + key);

        log.info("test: {}", stringBuilder.toString());

        //将结果进行MD5加密并将全部字符转换为大写
        return DigestUtils.md5DigestAsHex(stringBuilder.toString().getBytes()).toUpperCase();
    }

    /**
     * 将对象序列化为json格式
     *
     * @param source
     * @return
     */
    private static String serialize(Object source) {
        String json = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            StringWriter stringWriter = new StringWriter();
            JsonGenerator jsonGenerator = new JsonFactory().createGenerator(stringWriter);
            objectMapper.writeValue(jsonGenerator, source);
            jsonGenerator.close();
            json = stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSONObject.parseObject(json);

        //去除null
        return jsonObject.toJSONString();
    }
}
