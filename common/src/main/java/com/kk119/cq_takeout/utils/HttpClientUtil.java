package com.kk119.cq_takeout.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description HTTP 工具类
 */
@Slf4j
public class HttpClientUtil {

    /**
     * @Description 超时时间（毫秒）
     */
    static final int TIMEOUT_MS = 5 * 1000;

    /**
     * @param url      链接
     * @param paramMap 参数 map
     * @return {@link String }
     * @Description 发送 GET 请求
     */
    public static String doGet(String url, Map<String, String> paramMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String result = "";
        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(url);
            if (paramMap != null) {
                for (String key : paramMap.keySet()) {
                    builder.addParameter(key, paramMap.get(key));
                }
            }
            URI uri = builder.build();

            HttpGet httpGet = new HttpGet(uri);

            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            log.error("异常: ", e);
        } finally {
            try {
                response.close();
                httpClient.close();
            } catch (IOException ioe) {
                log.error("异常: ", ioe);
            }
        }

        return result;
    }

    /**
     * @param url      链接
     * @param paramMap 参数 map
     * @return {@link String }
     * @throws IOException 关闭链接时
     * @Description 发送 POST 请求
     */
    public static String doPost(String url, Map<String, String> paramMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            HttpPost httpPost = new HttpPost(url);

            if (paramMap != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (Map.Entry<String, String> param : paramMap.entrySet()) {
                    paramList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());

            response = httpClient.execute(httpPost);

            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } finally {
            try {
                response.close();
            } catch (IOException ioe) {
                log.error("异常: ", ioe);
            }
        }

        return resultString;
    }

    /**
     * @param url      url
     * @param paramMap param
     * @return {@link String }
     */
    public static String doPost4Json(String url, Map<String, String> paramMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        String resultString = "";

        try {
            HttpPost httpPost = new HttpPost(url);

            if (paramMap != null) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.putAll(paramMap);
                StringEntity entity = new StringEntity(jsonObject.toString(), "UTF-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }

            httpPost.setConfig(builderRequestConfig());
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            log.error("异常: ", e);
        } finally {
            try {
                response.close();
            } catch (IOException ioe) {
                log.error("异常: ", ioe);
            }
        }

        return resultString;
    }

    private static RequestConfig builderRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(TIMEOUT_MS)
                .setConnectionRequestTimeout(TIMEOUT_MS)
                .setSocketTimeout(TIMEOUT_MS)
                .build();
    }
}
