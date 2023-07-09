package com.jerry.common.dto.resp;

import lombok.Data;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Data
public class DriverUserExistsResp {

    private String driverPhone;

    private int exists;

}
