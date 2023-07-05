package com.jerry.apipassenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.apipassenger.service.token.TokenService;
import com.jerry.common.dto.TokenDTO;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.common.response.StatusCode;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/29
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService service;

    @PostMapping("/token-refresh")
    public JsonRespWrapper refreshToken(@RequestBody TokenDTO tokenDTO) {
        String refreshToken  =tokenDTO.getRefreshToken();

        TokenDTO respToken = service.refreshAccessToken(refreshToken);
        if (respToken == null) {
            return JsonRespWrapper.failure(StatusCode.SC_TOKEN_REFRESH_ERROR);
        }

        return JsonRespWrapper.success(respToken);
    }
}
