package com.jerry.servicemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.common.dto.req.PointReq;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicemap.remote.PointClient;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/9
 */
@Service
public class PointService {

    @Autowired
    private PointClient client;

    public JsonRespWrapper upload(PointReq pointReq) {
        return client.upload(pointReq);
    }

}
