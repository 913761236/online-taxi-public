package com.jerry.price.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.dto.ForecastPriceDTO;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@RestController
public class ForecastPriceController {

    @PostMapping("/forecast-price")
    public void forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {

    }

    @PostMapping("/calculate-price")
    public void calculatePrice(@RequestParam Integer distance, @RequestParam Integer duration,
        @RequestParam String cityCode, @RequestParam String vehicleType) {

    }
}
