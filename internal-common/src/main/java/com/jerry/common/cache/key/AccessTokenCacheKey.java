package com.jerry.common.cache.key;

import java.text.MessageFormat;

/**
 * Access Token 缓存键
 * 
 * @author qijie
 * @date 2023/6/6
 */
public abstract class AccessTokenCacheKey {

    private AccessTokenCacheKey() {

    }

    private static final String ACCESS_TOKEN_CACHE_KEY = "access-token:user:{0}:{1}";

    private static final String REFRESH_TOKEN_CACHE_KEY = "refresh-token:user:{0}:{1}";

    public static String getAccessTokenCacheKey(String phone, String identity) {
        return MessageFormat.format(ACCESS_TOKEN_CACHE_KEY, phone, identity);
    }

    public static String getRefreshTokenCacheKey(String phone, String identity) {
        return MessageFormat.format(REFRESH_TOKEN_CACHE_KEY, phone, identity);
    }
}
