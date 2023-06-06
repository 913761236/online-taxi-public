package com.jerry.apipassenger.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.apipassenger.client.user.PassengerUserClient;
import com.jerry.common.dto.AccessToken;
import com.jerry.common.dto.PassengerUser;
import com.jerry.common.response.JsonResponseWrapper;
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
    private PassengerUserClient userClient;

    public PassengerUser getUserByAccessToken(String accessToken) {
        AccessToken token = JwtUtil.parseToken(accessToken);
        String phone = token.getPhone();
        JsonResponseWrapper<PassengerUser> userWrapper = userClient.getUserByPhone(phone);
        return userWrapper.getData();
    }
}
