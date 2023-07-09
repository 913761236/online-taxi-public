package com.jerry.servicedriver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jerry.common.dto.DriverUserWorkStatus;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicedriver.service.DriverUserWorkStatusService;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@RestController
@RequestMapping("/driver-status")
public class DriverUserWorkStatusController {

    @Autowired
    private DriverUserWorkStatusService driverUserWorkStatusService;

    @PostMapping("/work-status")
    public JsonRespWrapper changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus) {
        return driverUserWorkStatusService.changeWorkStatus(driverUserWorkStatus.getDriverId(),
            driverUserWorkStatus.getWorkStatus());
    }

    @GetMapping("/work-status")
    public JsonRespWrapper<DriverUserWorkStatus> getWorkStatus(Long driverId) {
        return driverUserWorkStatusService.getWorkStatus(driverId);
    }

}
