package com.jerry.common.dto.req;

import lombok.Data;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/9
 */
@Data
public class ApiDriverPointReq {

    private Long carId;

    private PointDTO[] points;

}
