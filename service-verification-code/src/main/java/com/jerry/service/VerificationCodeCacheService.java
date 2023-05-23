package com.jerry.service;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/23
 */
public class VerificationCodeCacheService implements VerificationCodeService {
    @Override
    public String generateCode(String identityId) {
        return null;
    }

    @Override
    public boolean checkCode(String identityId, String code) {
        return false;
    }
}
