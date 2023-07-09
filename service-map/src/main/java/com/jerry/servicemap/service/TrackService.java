package com.jerry.servicemap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicemap.remote.TrackClient;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/9
 */
@Service
public class TrackService {

    @Autowired
    private TrackClient trackClient;

    public JsonRespWrapper add(String tid) {
        return trackClient.add(tid);
    }
}
