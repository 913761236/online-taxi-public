package com.jerry.servicedriver.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.jerry.common.dto.DriverUserWorkStatus;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicedriver.mapper.DriverUserWorkStatusMapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Service
public class DriverUserWorkStatusService {

    @Autowired
    private DriverUserWorkStatusMapper mapper;

    public JsonRespWrapper changeWorkStatus(Long driverId, Integer workStatus) {
        mapper.updateWorkStatus(driverId, workStatus);
        return JsonRespWrapper.success("");
    }

    public JsonRespWrapper<DriverUserWorkStatus> getWorkStatus(Long driverId) {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(1);
        params.put("driver_id", driverId);
        List<DriverUserWorkStatus> list = mapper.selectByMap(params);
        return JsonRespWrapper.success(list.get(0));
    }
}
