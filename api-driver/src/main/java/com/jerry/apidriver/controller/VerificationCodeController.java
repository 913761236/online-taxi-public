package com.jerry.apidriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.apidriver.service.VerificationCodeService;
import com.jerry.common.dto.VerificationCodeDTO;
import com.jerry.common.response.JsonRespWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/21
 */
@Slf4j
@RestController
public class VerificationCodeController {

    private final VerificationCodeService service;

    public VerificationCodeController(VerificationCodeService service) {
        this.service = service;
    }

    @GetMapping("/verification-code")
    public JsonRespWrapper verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String phone = verificationCodeDTO.getPassengerPhone();

        return service.createVerificationCode(phone);
    }

    @PostMapping("/verification-code-check")
    public JsonRespWrapper checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getCode();

        log.info("手机号" + passengerPhone + ",验证码：" + verificationCode);

        return service.checkCode(passengerPhone, verificationCode);
    }
}
