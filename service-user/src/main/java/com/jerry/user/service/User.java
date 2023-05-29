package com.jerry.user.service;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 用户实体
 *
 * @author qijie
 * @date 2023/5/23
 */
@Data
public class User {

    private Long id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private byte state;
}
