package com.jerry.common.dto.req;

import lombok.Data;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/9
 */
@Data
public class PointReq {

    private String tid;

    private String trId;

    private PointDTO[] points;

}
