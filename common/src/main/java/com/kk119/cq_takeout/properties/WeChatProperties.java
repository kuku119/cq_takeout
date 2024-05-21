package com.kk119.cq_takeout.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cq.wechat")
@Data
public class WeChatProperties {
    private String appId;   // 小程序 appid
    private String appSecret;   // 小程序密钥
    private String mchId;   // 商户号
    private String mchSerialNo; // 商户 API 证书序列号
    private String privateKeyFilePath;  // 商户私钥文件
    private String apiV3Key;    // 证书解密的密钥
    private String weChatPayCertFilePath;   // 平台证书
    private String notifyUrl;   // 支付成功的回调地址
    private String refundNotifyUrl; // 退款成功的回调地址
}
