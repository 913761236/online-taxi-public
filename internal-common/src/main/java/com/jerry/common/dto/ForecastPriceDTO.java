package com.jerry.common.dto;

import lombok.Data;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Data
public class ForecastPriceDTO {

    private String depLongitude;

    private String depLatitude;

    private String destLongitude;

    private String destLatitude;

    private String cityCode;

    private String vehicleType;

}
