package com.jerry.common.cache.key;

import java.text.MessageFormat;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/30
 */
public final class VerificationCodeCacheKey {

    private static final String VERIFICATION_CODE = "verification:phone:{0}:{1}";

    public static String getVerificationCodeKey(String phone, String identity) {
        return MessageFormat.format(VERIFICATION_CODE, phone, identity);
    }
}
