package com.jerry.apipassenger.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.apipassenger.service.user.PassengerUserService;
import com.jerry.common.dto.PassengerUser;
import com.jerry.common.response.JsonResponseWrapper;

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
    public JsonResponseWrapper<PassengerUser> getUser(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        return JsonResponseWrapper.success(userService.getUserByAccessToken(accessToken));
    }
}
