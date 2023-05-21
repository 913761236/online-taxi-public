package com.jerry.apipassenger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.apipassenger.dto.VerificationCodeDTO;
import com.jerry.apipassenger.service.VerificationCodeService;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/21
 */
@RestController
public class VerificationCodeController {

    private final VerificationCodeService service;

    public VerificationCodeController(VerificationCodeService service) {
        this.service = service;
    }

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String phone = verificationCodeDTO.getPassengerPhone();
        System.out.println(phone);
        return service.generateVerificationCode(phone);
    }
}
