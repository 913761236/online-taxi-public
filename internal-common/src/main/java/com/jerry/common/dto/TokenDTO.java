package com.jerry.common.dto;

import lombok.Data;

/**
 * @author qijie
 * @date 2023/6/6
 */
@Data
public class TokenDTO {

    private String accessToken;

    private String refreshToken;

    public TokenDTO() {

    }

    public TokenDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
