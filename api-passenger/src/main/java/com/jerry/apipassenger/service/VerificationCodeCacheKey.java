package com.jerry.apipassenger.service;

import java.text.MessageFormat;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/30
 */
public final class VerificationCodeCacheKey {

    private static final String VERIFICATION_CODE = "verification:phone:{0}";

    public static String getVerificationCodeKey(String phone) {
        return MessageFormat.format(VERIFICATION_CODE, phone);
    }
}
