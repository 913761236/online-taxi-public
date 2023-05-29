package com.jerry.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.user.mapper.UserMapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/23
 */
@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public boolean isUserExist(String phone) {
        return mapper.countUserByPhone(phone) > 0;
    }
}
