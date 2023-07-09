package com.jerry.apipassenger.remote.price;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jerry.common.dto.ForecastPriceDTO;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/7
 */
@FeignClient("service-price")
public interface ServicePriceClient {

    @PostMapping("/forecast-price")
    JsonRespWrapper<Double> forecast(@RequestBody ForecastPriceDTO forecastPriceDTO);

}
