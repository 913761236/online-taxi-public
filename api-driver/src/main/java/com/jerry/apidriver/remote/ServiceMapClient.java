package com.jerry.apidriver.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jerry.common.dto.req.PointReq;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/9
 */
@FeignClient("service-map")
public interface ServiceMapClient {

    @PostMapping("/map/point/upload")
    JsonRespWrapper upload(@RequestBody PointReq pointReq);

}
