package com.jerry.serviceprice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.dto.ForecastPriceDTO;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.serviceprice.service.PriceService;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@RestController
public class ForecastPriceController {

    @Autowired
    private PriceService forecastPriceService;

    @PostMapping("/forecast-price")
    public JsonRespWrapper forecastPrice(@RequestBody ForecastPriceDTO forecastPriceDTO) {
        String depLongitude = forecastPriceDTO.getDepLongitude();
        String depLatitude = forecastPriceDTO.getDepLatitude();
        String destLongitude = forecastPriceDTO.getDestLongitude();
        String destLatitude = forecastPriceDTO.getDestLatitude();
        String cityCode = forecastPriceDTO.getCityCode();
        String vehicleType = forecastPriceDTO.getVehicleType();

        return forecastPriceService.forecastPrice(depLongitude, depLatitude, destLongitude, destLatitude, cityCode,
            vehicleType);
    }

    @PostMapping("/calculate-price")
    public JsonRespWrapper calculatePrice(@RequestParam Integer distance, @RequestParam Integer duration,
        @RequestParam String cityCode, @RequestParam String vehicleType) {
        return forecastPriceService.calculatePrice(distance, duration, cityCode, vehicleType);
    }
}
