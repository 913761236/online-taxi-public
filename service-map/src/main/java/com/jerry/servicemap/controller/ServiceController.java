package com.jerry.servicemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicemap.service.ServiceFromMapService;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@RestController
@RequestMapping("/amap/service")
public class ServiceController {

    @Autowired
    private ServiceFromMapService serviceFromMapService;

    /**
     * 创建服务
     *
     */
    @PostMapping("/add")
    public JsonRespWrapper add(String name) {
        return serviceFromMapService.add(name);
    }

}
