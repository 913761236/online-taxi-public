package com.jerry.servicemap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.common.dto.resp.DirectionResp;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicemap.remote.MapDirectionClient;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/7
 */
@Service
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;

    /**
     * 预估路线和耗时
     */
    public JsonRespWrapper driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        DirectionResp direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);
        return JsonRespWrapper.success(direction);
    }
}
