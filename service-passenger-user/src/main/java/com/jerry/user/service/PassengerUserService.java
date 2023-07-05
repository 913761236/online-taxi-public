package com.jerry.user.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.common.dto.PassengerUser;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.common.response.StatusCode;
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

    public JsonRespWrapper getUserByPhone(String passengerPhone) {
        Map<String, Object> param = Collections.singletonMap("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = mapper.selectByMap(param);
        if (passengerUsers.isEmpty()) {
            return JsonRespWrapper.failure(StatusCode.SC_USER_NOT_EXISTS);
        }
        else {
            return JsonRespWrapper.success(passengerUsers.get(0));
        }
    }

    public JsonRespWrapper loginOrRegister(String passengerPhone) {
        // 用户不存在则创建用户
        if (!isUserExist(passengerPhone)) {
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("xxxxx");
            passengerUser.setPassengerGender((byte)0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte)0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setCreateTime(now);
            passengerUser.setUpdateTime(now);
            mapper.insert(passengerUser);
        }

        return JsonRespWrapper.success();
    }
}
