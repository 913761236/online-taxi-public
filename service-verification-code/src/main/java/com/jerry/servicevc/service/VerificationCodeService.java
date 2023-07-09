package com.jerry.servicevc.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/23
 */
@Service
public class VerificationCodeService {

    public String createCode(int size) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int num = random.nextInt((int)(Math.pow(10, size) * 0.9999999));

        return String.format("%0" + size + "d", num);
    }

}
