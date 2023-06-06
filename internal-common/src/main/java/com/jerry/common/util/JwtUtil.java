package com.jerry.common.util;

import java.util.Calendar;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jerry.common.dto.AccessToken;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/29
 */
public final class JwtUtil {

    /*
     * 生成token的盐
     */
    private static final String SLAT = "CXFAmdsz!d#$acl";

    private static final String JWT_KEY_PHONE = "phone";

    /**
     * 乘客是1，司机是2
     */
    private static final String JWT_KEY_IDENTITY = "identity";

    /**
     * token类型
     */
    private static final String JWT_TOKEN_TYPE = "tokenType";

    private static final String JWT_TOKEN_TIME = "tokenTime";

    public static String createToken(String phone, String identity, String tokenType) {
        JWTCreator.Builder builder = JWT.create();
        // 添加载荷
        builder.withClaim(JWT_KEY_PHONE, phone);
        builder.withClaim(JWT_KEY_IDENTITY, identity);
        builder.withClaim(JWT_TOKEN_TYPE, tokenType);
        // 记录token生成时间的同时也防止生成重复的token
        builder.withClaim(JWT_TOKEN_TIME, Calendar.getInstance().getTime().toString());
        return builder.sign(Algorithm.HMAC256(SLAT));
    }

    public static AccessToken parseToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SLAT)).build().verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        String phone = claims.get(JWT_KEY_PHONE).asString();
        String identity = claims.get(JWT_KEY_IDENTITY).asString();
        return new AccessToken(phone, identity);
    }

    public static AccessToken checkToken(String token) {
        AccessToken accessToken = null;
        try {
            accessToken = JwtUtil.parseToken(token);
        }
        catch (Exception ignored) {
            // 任何异常的产生都认为token无效
        }
        return accessToken;
    }
}
