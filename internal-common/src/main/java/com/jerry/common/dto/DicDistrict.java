package com.jerry.common.dto;

import lombok.Data;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Data
public class DicDistrict {

    private String addressCode;

    private String addressName;

    private String parentAddressCode;

    private Integer level;

}
