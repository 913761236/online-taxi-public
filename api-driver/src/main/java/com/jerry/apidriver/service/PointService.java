package com.jerry.apidriver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.apidriver.remote.ServiceDriverUserClient;
import com.jerry.apidriver.remote.ServiceMapClient;
import com.jerry.common.dto.Car;
import com.jerry.common.dto.req.ApiDriverPointReq;
import com.jerry.common.dto.req.PointReq;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/9
 */
@Service
public class PointService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public JsonRespWrapper upload(ApiDriverPointReq req) {
        // 获取carId
        Long carId = req.getCarId();

        // 通过carId 获取 tid、trid，调用 service-driver-user的接口
        JsonRespWrapper<Car> carById = serviceDriverUserClient.getCarById(carId);
        Car car = carById.getData();
        String tid = car.getTid();
        String trId = car.getTrid();

        // 调用高德地图接口上传
        PointReq pointRequest = new PointReq();
        pointRequest.setTid(tid);
        pointRequest.setTrId(trId);
        pointRequest.setPoints(req.getPoints());

        return serviceMapClient.upload(pointRequest);
    }
}
