package com.jerry.apipassenger.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/29
 */
@RestController
public class TokenController {

    @PostMapping("/token")
    public void token() {

    }

    @PostMapping("/")
    public void refreshToken() {

    }
}
