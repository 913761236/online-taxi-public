package com.jerry.apipassenger.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerry.common.dto.AccessToken;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.common.response.StatusCode;
import com.jerry.common.util.AccessTokenCacheKey;
import com.jerry.common.util.JwtUtil;

/**
 * @author qijie
 * @date 2023/6/6
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        boolean canAccess = true;
        String errorMessage = "";
        String token = request.getHeader("Authorization");
        // 未携带访问令牌
        if (!StringUtils.hasText(token)) {
            canAccess = false;
            errorMessage = "access token not found in header";
        }

        AccessToken accessToken = JwtUtil.checkToken(token);
        // 携带了访问令牌，判断是否有效
        String cacheKey = AccessTokenCacheKey.getAccessTokenCacheKey(accessToken.getPhone(), accessToken.getIdentity());
        String tokenInCache = redisTemplate.opsForValue().get(cacheKey);
        if (tokenInCache == null || !tokenInCache.equals(token)) {
            canAccess = false;
            errorMessage = "access token invalid";
        }

        if (!canAccess) {
            PrintWriter writer = response.getWriter();
            JsonRespWrapper<String> failure = JsonRespWrapper.failure(StatusCode.SC_ACCESS_DENIED);
            failure.setData(errorMessage);
            writer.println(mapper.writeValueAsString(failure));
        }

        return canAccess;
    }

}
