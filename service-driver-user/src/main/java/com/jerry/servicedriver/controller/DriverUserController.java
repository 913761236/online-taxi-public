package com.jerry.servicedriver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jerry.common.constast.DriverCarStatus;
import com.jerry.common.dto.DriverCarBindingRelation;
import com.jerry.common.dto.DriverUser;
import com.jerry.common.dto.resp.DriverUserExistsResp;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicedriver.service.DriverCarBindingService;
import com.jerry.servicedriver.service.DriverUserService;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@RestController
@RequestMapping("/driver-user")
public class DriverUserController {

    @Autowired
    private DriverUserService driverUserService;

    private DriverCarBindingService driverCarBindingService;

    @PostMapping("/user")
    public JsonRespWrapper addUser(@RequestBody DriverUser driverUser) {
        return driverUserService.addDriverUser(driverUser);
    }

    @PutMapping("/user")
    public JsonRespWrapper updateUser(@RequestBody DriverUser driverUser) {
        return driverUserService.updateDriverUser(driverUser);
    }

    @GetMapping("/check-if-exists/{driverPhone}")
    public JsonRespWrapper<DriverUserExistsResp> getUser(@PathVariable("driverPhone") String driverPhone) {
        JsonRespWrapper<DriverUser> driverUserResp = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDb = driverUserResp.getData();

        DriverUserExistsResp response = new DriverUserExistsResp();
        response.setDriverPhone(driverPhone);
        response.setExists(driverUserDb != null ? DriverCarStatus.DRIVER_EXISTS : DriverCarStatus.DRIVER_NOT_EXISTS);
        return JsonRespWrapper.success(response);
    }

    // /**
    // * 根据车辆Id查询司机信息
    // */
    // @GetMapping("/user/car/{carId}")
    // public JsonRespWrapper<OrderDriverResponse> getAvailableDriver(@PathVariable("carId") Long carId) {
    // return driverUserService.getAvailableDriver(carId);
    // }

    /**
     * 根据司机手机号查询司机和车辆绑定关系
     */
    @GetMapping("/driver-car-binding-relation")
    public JsonRespWrapper<DriverCarBindingRelation> getDriverCarRelationShip(@RequestParam String driverPhone) {
        return driverCarBindingService.getDriverCarRelationShipByDriverPhone(driverPhone);
    }

}
