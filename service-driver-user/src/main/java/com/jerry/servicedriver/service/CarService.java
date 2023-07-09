package com.jerry.servicedriver.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.jerry.common.dto.Car;
import com.jerry.common.dto.resp.TerminalResp;
import com.jerry.common.dto.resp.TrackResp;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicedriver.mapper.CarMapper;
import com.jerry.servicedriver.remote.ServiceMapClient;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Service
public class CarService {

    @Autowired
    private CarMapper mapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public JsonRespWrapper<Car> getCarById(Long carId) {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("id", carId);

        List<Car> cars = mapper.selectByMap(map);
        return JsonRespWrapper.success(cars.get(0));
    }

    public JsonRespWrapper addCar(Car car) {
        LocalDateTime now = LocalDateTime.now();
        car.setCreateTime(now);
        car.setUpdateTime(now);
        // 1. 保存车辆信息
        mapper.insert(car);

        // 2.获得此车辆的终端id：tid
        JsonRespWrapper<TerminalResp> responseResult =
            serviceMapClient.addTerminal(car.getVehicleNo(), car.getId().toString());
        String tid = responseResult.getData().getTid();
        car.setTid(tid);

        // 3.获得此车辆的轨迹id：trId
        JsonRespWrapper<TrackResp> trackResponseResponseResult = serviceMapClient.addTrack(tid);
        String trId = trackResponseResponseResult.getData().getTrId();
        String trName = trackResponseResponseResult.getData().getTrName();

        car.setTrid(trId);
        car.setTrname(trName);

        mapper.updateById(car);
        return JsonRespWrapper.success("");
    }
}
