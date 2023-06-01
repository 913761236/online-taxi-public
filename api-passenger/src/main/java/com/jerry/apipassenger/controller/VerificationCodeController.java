package com.jerry.apipassenger.controller;

import com.jerry.apipassenger.service.vcode.VerificationCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.apipassenger.dto.VerificationCodeDTO;
import com.jerry.common.response.JsonResponseWrapper;

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
    public JsonResponseWrapper<VerificationCodeDTO>
        verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String phone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = service.createVerificationCode(phone);
        verificationCodeDTO.setCode(verificationCode);
        return JsonResponseWrapper.success(verificationCodeDTO);
    }
}
