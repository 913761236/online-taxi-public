package com.jerry.service;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/23
 */
public interface VerificationCodeService {

    String generateCode(String identityId);

    boolean checkCode(String identityId, String code);

}
