package com.jerry.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * 司机状态
 *
 * @author qijie
 * @date 2023/7/8
 */
@Data
public class DriverUserWorkStatus implements Serializable {

    private Long id;

    private Long driverId;

    private Integer workStatus;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
