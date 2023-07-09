package com.jerry.servicedriver.service;

import static com.jerry.common.response.StatusCode.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.jerry.common.constast.DriverCarStatus;
import com.jerry.common.dto.DriverCarBindingRelation;
import com.jerry.common.dto.DriverUser;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.common.response.StatusCode;
import com.jerry.servicedriver.mapper.DriverCarBindingMapper;
import com.jerry.servicedriver.mapper.DriverUserMapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Service
public class DriverCarBindingService {

    @Autowired
    private DriverCarBindingMapper mapper;

    @Autowired
    private DriverUserMapper driverUserMapper;

    public JsonRespWrapper bind(DriverCarBindingRelation driverCarBindingRelation) {
        // 判断是否已经绑定过了，已经做过绑定，则不允许再次绑定
        QueryWrapper<DriverCarBindingRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelation.getDriverId());
        queryWrapper.eq("car_id", driverCarBindingRelation.getCarId());
        queryWrapper.eq("bind_state", DriverCarStatus.DRIVER_CAR_BIND);

        Long count = mapper.selectCount(queryWrapper);
        if (count.intValue() > 0) {
            return JsonRespWrapper.failure(DRIVER_CAR_BIND_EXISTS);
        }

        // 司机被绑定了
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", driverCarBindingRelation.getDriverId());
        queryWrapper.eq("bind_state", DriverCarStatus.DRIVER_CAR_BIND);
        count = mapper.selectCount(queryWrapper);
        if (count.intValue() > 0) {
            return JsonRespWrapper.failure(DRIVER_BIND_EXISTS);
        }

        // 车辆被绑定了
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("car_id", driverCarBindingRelation.getCarId());
        queryWrapper.eq("bind_state", DriverCarStatus.DRIVER_CAR_BIND);
        count = mapper.selectCount(queryWrapper);
        if (count.intValue() > 0) {
            return JsonRespWrapper.failure(CAR_BIND_EXISTS);
        }

        LocalDateTime now = LocalDateTime.now();
        driverCarBindingRelation.setBindingTime(now);

        driverCarBindingRelation.setBindState(DriverCarStatus.DRIVER_CAR_BIND);

        mapper.insert(driverCarBindingRelation);
        return JsonRespWrapper.success("");
    }

    public JsonRespWrapper unbind(DriverCarBindingRelation driverCarBindingRelation) {
        LocalDateTime now = LocalDateTime.now();

        Map<String, Object> map = Maps.newHashMapWithExpectedSize(3);
        map.put("driver_id", driverCarBindingRelation.getDriverId());
        map.put("car_id", driverCarBindingRelation.getCarId());
        map.put("bind_state", DriverCarStatus.DRIVER_CAR_BIND);

        List<DriverCarBindingRelation> list = mapper.selectByMap(map);
        if (list.isEmpty()) {
            return JsonRespWrapper.failure(StatusCode.DRIVER_CAR_BIND_NOT_EXISTS);
        }

        DriverCarBindingRelation relationship = list.get(0);
        relationship.setBindState(DriverCarStatus.DRIVER_CAR_UNBIND);
        relationship.setUnBindingTime(now);

        mapper.updateById(relationship);
        return JsonRespWrapper.success("");
    }

    public JsonRespWrapper<DriverCarBindingRelation> getDriverCarRelationShipByDriverPhone(String driverPhone) {
        QueryWrapper<DriverUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_phone", driverPhone);

        DriverUser driverUser = driverUserMapper.selectOne(queryWrapper);
        Long driverId = driverUser.getId();

        QueryWrapper<DriverCarBindingRelation> bingQueryWrapper = new QueryWrapper<>();
        bingQueryWrapper.eq("driver_id", driverId);
        bingQueryWrapper.eq("bind_state", DriverCarStatus.DRIVER_CAR_BIND);

        DriverCarBindingRelation binding = mapper.selectOne(bingQueryWrapper);
        return JsonRespWrapper.success(binding);

    }
}
