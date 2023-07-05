package com.jerry.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jerry.common.dto.VerificationCodeDTO;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.user.service.PassengerUserService;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/23
 */
@RestController
public class PassengerUserController {

    @Autowired
    private PassengerUserService userService;

    @PostMapping("/user")
    public JsonRespWrapper loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        return userService.loginOrRegister(passengerPhone);
    }

    @GetMapping("/user/{passengerPhone}")
    public JsonRespWrapper getUser(@PathVariable("passengerPhone") String passengerPhone) {
        return userService.getUserByPhone(passengerPhone);
    }

}
