package com.kk119.cq_takeout.utils;

import com.alibaba.fastjson.JSONObject;
import com.kk119.cq_takeout.properties.WeChatProperties;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @Description 微信支付工具类
 */
@Component
@Slf4j
public class WeChatPayUtil {
    // 支付接口
    public static final String JSAPI = "https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi";
    // 退款接口
    public static final String REFUNDS = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";

    @Autowired
    private WeChatProperties weChatProperties;

    /**
     * 获取调用微信接口的客户端工具对象
     *
     * @return {@link CloseableHttpClient }
     */
    private CloseableHttpClient getClient() {
        PrivateKey merchantPrivateKey = null;
        try {
            merchantPrivateKey = PemUtil.loadPrivateKey(new FileInputStream(weChatProperties.getPrivateKeyFilePath()));
            X509Certificate x509Certificate = PemUtil.loadCertificate(new FileInputStream(weChatProperties.getWeChatPayCertFilePath()));
            List<X509Certificate> wechatPayCertificates = Collections.singletonList(x509Certificate);

            WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                    .withMerchant(weChatProperties.getMchId(), weChatProperties.getMchSerialNo(), merchantPrivateKey)
                    .withWechatPay(wechatPayCertificates);

            return builder.build();
        } catch (FileNotFoundException fnfe) {
            log.error("异常: ", fnfe);
            return null;
        }
    }

    /**
     * 发送 POST 请求
     *
     * @param url  url
     * @param body body
     * @return {@link String }
     */
    private String post(String url, String body) throws Exception {
        CloseableHttpClient httpClient = getClient();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
        httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        httpPost.addHeader("Wechatpay-Serial", weChatProperties.getMchSerialNo());
        httpPost.setEntity(new StringEntity(body, "UTF-8"));

        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            httpClient.close();
            response.close();
        }
    }

    /**
     * 发送 GET 请求
     *
     * @return {@link String }
     */
    private String get(String url) throws Exception {
        CloseableHttpClient httpClient = getClient();

        HttpGet httpGet = new HttpGet(url);

        httpGet.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
        httpGet.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
        httpGet.addHeader("Wechatpay-Serial", weChatProperties.getMchSerialNo());

        CloseableHttpResponse response = httpClient.execute(httpGet);

        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            httpClient.close();
            response.close();
        }
    }

    /**
     * jsapi 下单
     *
     * @param orderNum    订单号
     * @param total       金额（元）
     * @param description 商品描述
     * @param openid      用户的 openid
     * @return {@link String }
     */
    private String jsapi(String orderNum, BigDecimal total, String description, String openid) throws Exception {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("appid", weChatProperties.getAppId());
        jsonObject.put("mchid", weChatProperties.getMchId());
        jsonObject.put("description", description);
        jsonObject.put("out_trade_no", orderNum);
        jsonObject.put("notify_url", weChatProperties.getNotifyUrl());

        JSONObject amount = new JSONObject();

        amount.put("total", total.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP).intValue());
        amount.put("currency", "CNY");

        jsonObject.put("amount", amount);

        JSONObject payer = new JSONObject();
        payer.put("openid", openid);

        jsonObject.put("payer", payer);

        String body = jsonObject.toJSONString();

        return post(JSAPI, body);
    }

    /**
     * 小程序支付
     *
     * @param orderNum    订单号
     * @param total       金额（元）
     * @param description 商品描述
     * @param openid      用户的 openid
     * @return {@link JSONObject }
     */
    public JSONObject pay(String orderNum, BigDecimal total, String description, String openid) throws Exception {
        String bodyAsString = jsapi(orderNum, total, description, openid);
        JSONObject jsonObject = JSONObject.parseObject(bodyAsString);

//        log.info(String.valueOf(jsonObject));

        String prepayId = jsonObject.getString("prepay_id");
        if (prepayId != null) {
            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
            String nonceStr = RandomStringUtils.randomNumeric(32);

            ArrayList<Object> list = new ArrayList<>();

            list.add(weChatProperties.getAppId());
            list.add(timeStamp);
            list.add(nonceStr);
            list.add("prepay_id=" + prepayId);

            StringBuilder stringBuilder = new StringBuilder();

            for (Object obj : list) {
                stringBuilder.append(obj.toString()).append("\n");
            }

            String signMessage = stringBuilder.toString();
            byte[] message = signMessage.getBytes();

            Signature signature = Signature.getInstance("SHA256withRSA");

            signature.initSign(PemUtil.loadPrivateKey(Files.newInputStream(new File(weChatProperties.getPrivateKeyFilePath()).toPath())));
            signature.update(message);

            String packageSign = Base64.getEncoder().encodeToString(signature.sign());

            JSONObject jo = new JSONObject();
            jo.put("timestamp", timeStamp);
            jo.put("nonceStr", nonceStr);
            jo.put("package", "prepay_id=" + prepayId);
            jo.put("signType", "RSA");
            jo.put("paySign", packageSign);

            return jo;
        }

        return jsonObject;
    }

    /**
     * 申请退款
     *
     * @param outTradeNo  订单号
     * @param outRefundNo 退款单号
     * @param refund      退款金额
     * @param total       原订单金额
     * @return {@link String }
     */
    public String refund(String outTradeNo, String outRefundNo, BigDecimal refund, BigDecimal total) throws Exception {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("out_trade_no", outTradeNo);
        jsonObject.put("out_refund_no", outRefundNo);

        JSONObject amount = new JSONObject();

        amount.put("refund", refund.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP).intValue());
        amount.put("total", total.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP).intValue());
        amount.put("currency", "CNY");

        jsonObject.put("amount", amount);
        jsonObject.put("notify_url", weChatProperties.getRefundNotifyUrl());

        String body = jsonObject.toJSONString();

        return post(REFUNDS, body);
    }
}
