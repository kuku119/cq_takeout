package com.kk119.cq_takeout.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @Description JWT 加密类
 * */
public class JwtUtil {
    /**
     * 生成 jwt
     * 使用 HS256 算法，私钥使用固定密钥
     *
     * @param secretKey jwt 密钥
     * @param ttlMillis jwt 过期时间（毫秒）
     * @param claims    设置的信息
     *
     * @return  jwt
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * token 解密
     *
     * @param secretKey jwt 密钥
     * @param token 加密后的 token
     *
     * @return  解密后的 token
     */
    public static Claims parseJWT(String secretKey, String token) {

        return Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJwt(token).getBody();
    }
}
