package com.zzmall.api.repositor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.zzmall.api.common.config.PayjsProperties;
import com.zzmall.api.common.dto.NativeRequestDTO;
import com.zzmall.api.common.dto.PayCheckDTO;
import com.zzmall.api.common.dto.NativeResponseDTO;
import com.zzmall.api.pojo.User;
import com.zzmall.api.util.EncryptUtil;
import com.zzmall.api.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;


/**
 * @Author Connor
 * @Date 2019/05/18 16:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositorTest {

    @Autowired
    private PayjsProperties payjsProperties;

    @Autowired
    private UserRepositor userRepositor;

    @Test
    public void test() {

//        User result = userRepositor.getUserByUsername("admin");
////        Assert.assertEquals("admin", result.getUsername());
//
//        String serialize = KeyUtil.getSign(new User(), "afasdfasdf");

//        log.info("序列化为json：{}", serialize);

        String orderNo = "2019052815392500598579456";

        PayCheckDTO payCheckDTO = new PayCheckDTO();
        payCheckDTO.setPayjs_order_id(orderNo);
        payCheckDTO.setSign(KeyUtil.getSign(payCheckDTO, payjsProperties.getKey()));
        HttpRequest httpRequest = HttpRequest.get(
                "https://payjs.cn/api/check",
                true,
                "payjs_order_id",
                orderNo,
                "sign",
                payCheckDTO.getSign()
        );
        log.info("request:{}", httpRequest.toString());
        log.info("body:{}", httpRequest.body());

    }

    @Test
    public void pay() {

        final String orderNo = "815392500598579456";
        final String baseUrl = "https://payjs.cn/api/native";

        NativeRequestDTO nativeRequestDTO = new NativeRequestDTO();

        nativeRequestDTO.setMchid(payjsProperties.getMchid());
        nativeRequestDTO.setBody("我的订单标题");
        nativeRequestDTO.setTotalFee(new BigDecimal(23));
        nativeRequestDTO.setOut_trade_no(orderNo);
        nativeRequestDTO.setSign(KeyUtil.getSign(nativeRequestDTO, payjsProperties.getKey()));

        Map<String, String> param = new HashMap<>();

        param.put("body", nativeRequestDTO.getBody());
        param.put("mchid", nativeRequestDTO.getMchid());
        param.put("out_trade_no", nativeRequestDTO.getOut_trade_no());
        param.put("sign", nativeRequestDTO.getSign());
        param.put("total_fee", nativeRequestDTO.getTotal_fee().toString());

        HttpRequest httpRequest = HttpRequest.get(baseUrl, param, true);

        log.info("request:{}", httpRequest.toString());
        log.info("body:{}", httpRequest.body());
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

    @Test
    public void test2() {

        User user = userRepositor.getUserByUsername("abcd");

        log.info("user: {}", user);

    }

    @Test
    public void test3() {
//        log.info("唯一主键：{}", KeyUtil.genUniqueKey());
        List<String> strings = new ArrayList<>();
        strings.add("mchid");
        strings.add("totalFee");
        strings.add("outTradeNo");
        strings.add("body");
        strings.add("sign");

        Collections.sort(strings);

        log.info("set:{}", strings);
    }

    @Test
    public void test4() {
        String jsonString = "{\"return_code\":1,\"return_msg\":\"SUCCESS\",\"payjs_order_id\":\"2019052815392500598579456\",\"out_trade_no\":\"201905281539249965282\",\"total_fee\":\"1\",\"qrcode\":\"https://payjs.cn/qrcode/d2VpeGluOi8vd3hwYXkvYml6cGF5dXJsP3ByPTI2OVN0R04=\",\"code_url\":\"weixin://wxpay/bizpayurl?pr=269StGN\"}";

        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        log.info("jsonobject:{}", jsonObject.toJSONString());

        NativeResponseDTO nativeResponseDTO = JSON.parseObject(jsonObject.toJSONString(), NativeResponseDTO.class);
        log.info("responseDTO:{}", nativeResponseDTO);

    }

    @Test
    public void test5() {

        NativeRequestDTO nativeRequestDTO = new NativeRequestDTO();

        nativeRequestDTO.setOut_trade_no("1234235235235");

        String sign = KeyUtil.getSign(nativeRequestDTO, "asdfasdf");



    }

}