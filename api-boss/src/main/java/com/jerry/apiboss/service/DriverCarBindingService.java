package com.jerry.apiboss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.apiboss.remote.ServiceDriverUserClient;
import com.jerry.common.dto.DriverCarBindingRelation;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Service
public class DriverCarBindingService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public JsonRespWrapper bind(DriverCarBindingRelation driverCarBindingRelation) {
        return serviceDriverUserClient.bind(driverCarBindingRelation);
    }

    public JsonRespWrapper unbind(DriverCarBindingRelation driverCarBindingRelation) {
        return serviceDriverUserClient.unbind(driverCarBindingRelation);
    }
}
