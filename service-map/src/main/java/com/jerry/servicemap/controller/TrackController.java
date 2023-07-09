package com.jerry.servicemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicemap.service.TrackService;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@RestController
@RequestMapping("/map/track")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @PostMapping("/add")
    public JsonRespWrapper add(String tid) {
        return trackService.add(tid);
    }
}
