package com.zzmall.api.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Connor
 * @Date 2019/05/28 15:33
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "payjs")
public class PayjsProperties {

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 通信密钥
     */
    private String key;

}
