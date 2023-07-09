package com.jerry.apiboss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.apiboss.remote.ServiceDriverUserClient;
import com.jerry.common.dto.Car;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Service
public class CarService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public JsonRespWrapper addCar(Car car) {
        return serviceDriverUserClient.addCar(car);
    }
}
