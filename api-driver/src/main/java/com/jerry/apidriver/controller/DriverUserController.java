package com.jerry.apidriver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.apidriver.service.DriverUserService;
import com.jerry.common.dto.DriverUser;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@RestController
@RequestMapping("/driver-user")
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    @PutMapping("/user")
    public JsonRespWrapper updateUser(@RequestBody DriverUser driverUser) {
        return driverUserService.updateDriverUser(driverUser);
    }

}
