package com.jerry.common.dto.resp;

import lombok.Data;

/**
 * 估价
 *
 * @author qijie
 * @date 2023/7/7
 */
@Data
public class ForecastPriceResp {

    private double price;

    private String cityCode;

    private String vehicleType;

    private String fareType;

    private Integer fareVersion;
}
