package com.jerry.apipassenger.service.vcode;

import com.jerry.common.response.JsonRespWrapper;

/**
 * @author qijie
 * @date 2023/5/30
 */
public interface VerificationCodeService {

    /**
     * 为了方便测试，验证码在接口中返回
     */
    JsonRespWrapper createVerificationCode(String phone);

    JsonRespWrapper checkCode(String phone, String code);

}
