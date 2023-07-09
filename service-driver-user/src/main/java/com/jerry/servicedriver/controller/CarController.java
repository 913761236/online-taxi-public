package com.jerry.servicedriver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jerry.common.dto.Car;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicedriver.service.CarService;

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

    @GetMapping
    public JsonRespWrapper<Car> getCarById(Long carId) {
        return service.getCarById(carId);
    }

    @PostMapping
    public JsonRespWrapper addCar(@RequestBody Car car) {
        return service.addCar(car);
    }
}
