package com.jerry.apiboss.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jerry.common.dto.Car;
import com.jerry.common.dto.DriverCarBindingRelation;
import com.jerry.common.dto.DriverUser;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @PostMapping("/driver-user/user")
    JsonRespWrapper addDriverUser(@RequestBody DriverUser driverUser);

    @PutMapping("/driver-user/user")
    JsonRespWrapper updateDriverUser(@RequestBody DriverUser driverUser);

    @PostMapping("/car")
    JsonRespWrapper addCar(@RequestBody Car car);

    @PostMapping("/driver-car-binding/bind")
    JsonRespWrapper bind(@RequestBody DriverCarBindingRelation driverCarBindingRelation);

    @PostMapping("/driver-car-binding/unbind")
    JsonRespWrapper unbind(DriverCarBindingRelation driverCarBindingRelation);

}
