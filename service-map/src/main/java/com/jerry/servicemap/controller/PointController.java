package com.jerry.servicemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.dto.req.PointReq;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@RestController
@RequestMapping("/map/point")
public class PointController {

    @Autowired
    private PointService service;

    @PostMapping("/upload")
    public JsonRespWrapper upload(@RequestBody PointReq pointReq) {
        return service.upload(pointReq);
    }
}
