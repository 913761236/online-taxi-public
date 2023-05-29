package com.jerry.apipassenger.token;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

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

    public static String createToken(Map<String, String> payload) {
        JWTCreator.Builder builder = JWT.create();
        // 添加载荷
        payload.forEach(builder::withClaim);
        return builder.sign(Algorithm.HMAC256(SLAT));
    }

    public static Map<String, String> parseToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SLAT)).build().verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        return claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().asString()));
    }

    public static void main(String[] args) {
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "张三");
        payload.put("phone", "138xxxxxx55");
        System.out.println(parseToken(createToken(payload)));

        ThreadLocalRandom random = ThreadLocalRandom.current();
        int num = random.nextInt(999999);
        String code = String.format("%06d", num);
        System.out.println(code);
    }
}
