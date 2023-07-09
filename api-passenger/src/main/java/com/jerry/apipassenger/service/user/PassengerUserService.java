package com.jerry.apipassenger.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.apipassenger.remote.user.ServicePassengerUserClient;
import com.jerry.common.dto.AccessToken;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.common.util.JwtUtil;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Service
public class PassengerUserService {

    @Autowired
    private ServicePassengerUserClient userClient;

    public JsonRespWrapper getUserByAccessToken(String accessToken) {
        AccessToken token = JwtUtil.parseToken(accessToken);
        String phone = token.getPhone();
        return userClient.getUserByPhone(phone);
    }
}
