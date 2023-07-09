package com.jerry.serviceprice.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jerry.common.dto.ForecastPriceDTO;
import com.jerry.common.dto.resp.DirectionResp;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@FeignClient("service-map")
public interface ServiceMapClient {

    @PostMapping("/direction/driving")
    JsonRespWrapper<DirectionResp> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);

}
