package com.jerry.apipassenger.service.vcode;

/**
 * @author qijie
 * @date 2023/5/30
 */
public interface VerificationCodeService {

    String createVerificationCode(String phone);

    boolean checkCode(String phone, String code);

}
