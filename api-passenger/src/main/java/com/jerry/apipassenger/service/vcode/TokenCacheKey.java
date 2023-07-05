package com.jerry.apipassenger.service.vcode;

import java.text.MessageFormat;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/6
 */
public final class TokenCacheKey {

    private static final String ACCESS_TOKEN = "token:access:{0}:{1}";

    private static final String REFRESH_TOKEN = "token:refresh:{0}:{1}";

    public static String getAccessTokenCacheKey(String phone, String identity) {
        return MessageFormat.format(ACCESS_TOKEN, phone, identity);
    }

    public static String getRefreshTokenCacheKey(String phone, String identity) {
        return MessageFormat.format(REFRESH_TOKEN, phone, identity);
    }
}
