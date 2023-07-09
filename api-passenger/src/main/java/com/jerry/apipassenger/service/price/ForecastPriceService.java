package com.jerry.apipassenger.service.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.apipassenger.remote.price.ServicePriceClient;
import com.jerry.common.dto.ForecastPriceDTO;
import com.jerry.common.response.JsonRespWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/7
 */
@Slf4j
@Service
public class ForecastPriceService {

    @Autowired
    private ServicePriceClient servicePriceClient;

    /**
     * 根据 出发地和目的地经纬度 计算预估价格
     */
    public JsonRespWrapper forecastPrice(String depLongitude, String depLatitude, String destLongitude,
        String destLatitude, String cityCode, String vehicleType) {

        log.info("出发地经度：" + depLongitude);
        log.info("出发地纬度：" + depLatitude);
        log.info("目的地经度：" + destLongitude);
        log.info("目的地纬度：" + destLatitude);

        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);
        forecastPriceDTO.setCityCode(cityCode);
        forecastPriceDTO.setVehicleType(vehicleType);
        JsonRespWrapper<Double> respWrapper = servicePriceClient.forecast(forecastPriceDTO);

        return JsonRespWrapper.success(respWrapper.getData());
    }
}
