package com.jerry.servicemap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicemap.remote.MapServiceClient;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Service
public class ServiceFromMapService {

    @Autowired
    private MapServiceClient serviceClient;

    public JsonRespWrapper add(String name) {
        return serviceClient.add(name);
    }

}
