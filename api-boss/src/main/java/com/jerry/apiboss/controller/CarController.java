package com.jerry.apiboss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.apiboss.service.CarService;
import com.jerry.common.dto.Car;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping
    public JsonRespWrapper addCar(@RequestBody Car car) {
        return service.addCar(car);
    }
}