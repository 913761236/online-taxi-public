package com.jerry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.response.JsonResponseWrapper;
import com.jerry.service.VerificationCodeService;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/21
 */
@RestController
public class NumberCodeController {

    @Autowired
    private VerificationCodeService service;

    @GetMapping("/verification-code/{size}")
    public JsonResponseWrapper<String> numberCode(@PathVariable Integer size) {
        if (size <= 4) {
            size = 4;
        }

        if (size >= 8) {
            size = 8;
        }
        String code = service.createCode(size);
        return JsonResponseWrapper.success(code);
    }

}
