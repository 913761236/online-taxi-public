package com.jerry.apipassenger.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.apipassenger.service.user.PassengerUserService;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@RestController
public class UserController {

    @Autowired
    private PassengerUserService userService;

    @GetMapping("/user")
    public JsonRespWrapper getUser(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        return JsonRespWrapper.success(userService.getUserByAccessToken(accessToken));
    }
}
