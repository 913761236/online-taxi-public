package com.jerry.apidriver.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.jerry.common.dto.Car;
import com.jerry.common.dto.DriverCarBindingRelation;
import com.jerry.common.dto.DriverUser;
import com.jerry.common.dto.DriverUserWorkStatus;
import com.jerry.common.dto.resp.DriverUserExistsResp;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {

    @PutMapping("/driver-user/user")
    JsonRespWrapper updateDriverUser(@RequestBody DriverUser driverUser);

    @GetMapping("/driver-user/check-if-exists/{driverPhone}")
    JsonRespWrapper<DriverUserExistsResp> checkDriver(@PathVariable("driverPhone") String driverPhone);

    @GetMapping("/car")
    JsonRespWrapper<Car> getCarById(@RequestParam Long carId);

    @PostMapping("/driver-user-work-status")
    JsonRespWrapper changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus);

    @GetMapping("/driver-status/work-status")
    JsonRespWrapper<DriverUserWorkStatus> getWorkStatus(@RequestParam Long driverId);

    @GetMapping("/driver-car-binding-relation")
    JsonRespWrapper<DriverCarBindingRelation> getDriverCarRelationShip(@RequestParam String driverPhone);

}
