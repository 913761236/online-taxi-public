package com.jerry.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.dto.PassengerUser;
import com.jerry.common.response.JsonResponseWrapper;
import com.jerry.common.response.StatusCode;
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

    public void user() {

    }

    @GetMapping("/user/{passengerPhone}")
    public JsonResponseWrapper getUser(@PathVariable("passengerPhone") String passengerPhone) {
        PassengerUser user = userService.getUserByPhone(passengerPhone);
        if (user == null) {
            return JsonResponseWrapper.failure(StatusCode.SC_USER_NOT_EXISTS);
        }
        else {
            return JsonResponseWrapper.success(user);
        }
    }

}
