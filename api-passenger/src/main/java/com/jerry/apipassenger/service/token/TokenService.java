package com.jerry.apipassenger.service.token;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jerry.common.cache.key.AccessTokenCacheKey;
import com.jerry.common.dto.AccessToken;
import com.jerry.common.dto.TokenDTO;
import com.jerry.common.util.JwtUtil;
import com.jerry.common.util.TokenType;

/**
 * @author qijie
 * @date 2023/6/6
 */
@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public TokenDTO refreshAccessToken(String refreshTokenSrc) {
        AccessToken refreshToken = JwtUtil.checkToken(refreshTokenSrc);
        if (refreshToken == null) {
            return null;
        }

        String phone = refreshToken.getPhone();
        String identity = refreshToken.getIdentity();

        String refreshTokenCacheKey = AccessTokenCacheKey.getRefreshTokenCacheKey(phone, identity);
        String refreshTokenInCache = redisTemplate.opsForValue().get(refreshTokenCacheKey);

        if (!StringUtils.hasText(refreshTokenInCache) || !refreshTokenInCache.equals(refreshTokenSrc)) {
            return null;
        }

        String accessTokenNew = JwtUtil.createToken(phone, identity, TokenType.ACCESS_TOKEN);
        String refreshTokenNew = JwtUtil.createToken(phone, identity, TokenType.REFRESH_TOKEN);

        String accessTokenCacheKey = AccessTokenCacheKey.getAccessTokenCacheKey(phone, identity);
        redisTemplate.opsForValue().set(accessTokenCacheKey, accessTokenNew, 30, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(refreshTokenCacheKey, refreshTokenNew, 31, TimeUnit.DAYS);
        return new TokenDTO(accessTokenNew, refreshTokenNew);
    }
}
