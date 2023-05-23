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

    private String phone;

    private String userName;

    private byte gender;

    private byte state;
}
