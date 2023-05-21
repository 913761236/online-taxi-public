package com.jerry.apipassenger.service;

import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/21
 */
@Service
public class VerificationCodeService {

    public String generateVerificationCode(String phone) {
        return "112";
    }
}
