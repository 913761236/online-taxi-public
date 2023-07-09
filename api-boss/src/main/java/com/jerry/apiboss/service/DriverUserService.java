package com.jerry.apiboss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.apiboss.remote.ServiceDriverUserClient;
import com.jerry.common.dto.DriverUser;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Service
public class DriverUserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public JsonRespWrapper addDriverUser(DriverUser user) {
        return serviceDriverUserClient.addDriverUser(user);
    }

    public JsonRespWrapper updateDriverUser(DriverUser driverUser) {
        return serviceDriverUserClient.updateDriverUser(driverUser);
    }
}
