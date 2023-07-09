package com.jerry.servicedriver.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.jerry.common.constast.DriverCarStatus;
import com.jerry.common.dto.DriverUser;
import com.jerry.common.dto.DriverUserWorkStatus;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicedriver.mapper.DriverUserMapper;
import com.jerry.servicedriver.mapper.DriverUserWorkStatusMapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Service
public class DriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    public JsonRespWrapper addDriverUser(DriverUser user) {
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);

        driverUserMapper.insert(user);

        // 同时初始化司机工作状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(user.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarStatus.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setCreateTime(now);
        driverUserWorkStatus.setUpdateTime(now);
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);

        return JsonRespWrapper.success("");
    }

    public JsonRespWrapper updateDriverUser(DriverUser driverUser) {
        LocalDateTime now = LocalDateTime.now();
        driverUser.setUpdateTime(now);
        return JsonRespWrapper.success("");
    }

    public JsonRespWrapper<DriverUser> getDriverUserByPhone(String driverPhone) {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        map.put("driver_phone", driverPhone);
        map.put("state", DriverCarStatus.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        if (driverUsers.isEmpty()) {
            return JsonRespWrapper.success(null);
        }
        DriverUser driverUser = driverUsers.get(0);
        return JsonRespWrapper.success(driverUser);
    }
}
