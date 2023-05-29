package com.jerry.apipassenger.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/21
 */
@Service
public class VerificationCodeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 生成随机的6位数的验证码
     * 
     * @param phone 用户手机号
     * @return 验证码
     */
    public String createVerificationCode(String phone) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int num = random.nextInt(999999);
        String code = String.format("%06d", num);
        redisTemplate.boundValueOps(VerificationCodeCacheKey.getVerificationCodeKey(phone)).set(code);
        return code;
    }

    public boolean checkVerificationCode(String phone, String code) {
        String codeInServer = redisTemplate.boundValueOps(VerificationCodeCacheKey.getVerificationCodeKey(phone)).get();
        return code != null && code.equals(codeInServer);
    }
}
