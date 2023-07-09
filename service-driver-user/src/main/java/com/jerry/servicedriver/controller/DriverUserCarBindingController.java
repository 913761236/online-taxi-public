package com.jerry.servicedriver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.dto.DriverCarBindingRelation;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicedriver.service.DriverCarBindingService;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@RestController
@RequestMapping("/driver-car-binding")
public class DriverUserCarBindingController {

    @Autowired
    private DriverCarBindingService service;

    @PostMapping("/bind")
    public JsonRespWrapper bind(@RequestBody DriverCarBindingRelation driverCarBindingRelation) {
        return service.bind(driverCarBindingRelation);
    }

    @PostMapping("/unbind")
    public JsonRespWrapper unbind(@RequestBody DriverCarBindingRelation driverCarBindingRelation) {
        return service.unbind(driverCarBindingRelation);

    }

}
