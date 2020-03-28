package com.cloud.platform.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    /**
     * 过期时间为一天
     * TODO 正式上线更换
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * Token 私钥
     */
    private static final String TOKEN_SECERT = "joijsdfjlsjfljfljl5135313135";

    /**
     * 生成签名
     *
     * @param username
     * @param userId
     * @return
     */
    public static String sign(String username, Integer userId) {
        //设置过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //通过私钥配置加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECERT);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userId信息生成签名
        return JWT.create().withHeader(header)
                .withClaim("username", username).withClaim("userId", userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECERT);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (IllegalArgumentException | JWTVerificationException e){
            return false;
        }

    }

}
