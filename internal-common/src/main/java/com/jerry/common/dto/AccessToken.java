package com.jerry.common.dto;

import lombok.Data;

/**
 * @author qijie
 * @date 2023/6/6
 */
@Data
public class AccessToken {

    private String phone;

    private String identity;

    public AccessToken() {

    }

    public AccessToken(String phone, String identity) {
        this.phone = phone;
        this.identity = identity;
    }
}
