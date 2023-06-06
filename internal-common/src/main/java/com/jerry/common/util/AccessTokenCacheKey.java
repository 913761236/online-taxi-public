package com.jerry.common.util;

import java.text.MessageFormat;

/**
 * @author qijie
 * @date 2023/6/6
 */
public class AccessTokenCacheKey {

    private static final String ACCESS_TOKEN_CACHE_KEY = "access-token:user:{0}:{1}";

    private static final String REFRESH_TOKEN_CACHE_KEY = "refresh-token:user:{0}:{1}";


    public static String getAccessTokenCacheKey(String phone, String identity) {
        return MessageFormat.format(ACCESS_TOKEN_CACHE_KEY, phone, identity);
    }

    public static String getRefreshTokenCacheKey(String phone, String identity){
        return MessageFormat.format(REFRESH_TOKEN_CACHE_KEY, phone, identity);
    }
}
