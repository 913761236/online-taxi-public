package com.jerry.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Data
public class DriverCarBindingRelation implements Serializable {

    private Long id;

    /**
     * 司机ID
     */
    private Long driverId;

    /**
     * 车辆Id
     */
    private Long carId;

    /**
     * 绑定状态：1：绑定，2：解绑
     */
    private Integer bindState;

    /**
     * 绑定时间
     */
    private LocalDateTime bindingTime;

    /**
     * 解绑时间
     */
    private LocalDateTime unBindingTime;

}
