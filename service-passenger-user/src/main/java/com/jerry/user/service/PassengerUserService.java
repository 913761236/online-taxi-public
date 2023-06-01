package com.jerry.user.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.common.dto.PassengerUser;
import com.jerry.user.mapper.PassengerUserMapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/23
 */
@Service
public class PassengerUserService {

    @Autowired
    private PassengerUserMapper mapper;

    public boolean isUserExist(String phone) {
        return mapper.countUserByPhone(phone) > 0;
    }

    public PassengerUser getUserByPhone(String passengerPhone) {
        Map<String, Object> param = Collections.singletonMap("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = mapper.selectByMap(param);
        if (passengerUsers.isEmpty()) {
            return null;
        }
        else {
            return passengerUsers.get(0);
        }
    }

}
