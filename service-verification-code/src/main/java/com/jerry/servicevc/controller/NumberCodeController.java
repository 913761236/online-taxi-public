package com.jerry.servicevc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicevc.service.VerificationCodeService;

/**
 * 验证码服务接口
 *
 * @author qijie
 * @date 2023/5/21
 */
@RestController
public class NumberCodeController {

    @Autowired
    private VerificationCodeService service;

    @GetMapping("/verification-code/{size}")
    public JsonRespWrapper<String> numberCode(@PathVariable Integer size) {
        // 限制验证码的长度
        if (size <= 4) {
            size = 4;
        }

        if (size >= 8) {
            size = 8;
        }
        String code = service.createCode(size);
        return JsonRespWrapper.success(code);
    }

}
