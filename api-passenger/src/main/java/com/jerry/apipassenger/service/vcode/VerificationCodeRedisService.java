package com.jerry.apipassenger.service.vcode;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.jerry.apipassenger.client.vcode.VerificationCodeClient;
import com.jerry.common.response.JsonResponseWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/23
 */
@Service
public class VerificationCodeRedisService implements VerificationCodeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private VerificationCodeClient verificationCodeClient;

    @Override
    public String createVerificationCode(String phone) {
        JsonResponseWrapper<String> response = verificationCodeClient.createCode(6);
        String code = response.getData();
        redisTemplate.boundValueOps(VerificationCodeCacheKey.getVerificationCodeKey(phone))
            .set(response.getData(), Duration.ofMinutes(5));
        return code;
    }

    @Override
    public boolean checkCode(String phone, String code) {
        String codeInServer = redisTemplate.boundValueOps(VerificationCodeCacheKey.getVerificationCodeKey(phone)).get();
        return code != null && code.equals(codeInServer);
    }

}
