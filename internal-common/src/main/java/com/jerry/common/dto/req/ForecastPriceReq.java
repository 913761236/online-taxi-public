package com.jerry.common.dto.req;

import lombok.Data;

/**
 * 估价
 *
 * @author qijie
 * @date 2023/7/7
 */
@Data
public class ForecastPriceReq {

    private String depLongitude;

    private String depLatitude;

    private String destLongitude;

    private String destLatitude;

    private String cityCode;

    private String vehicleType;

}
