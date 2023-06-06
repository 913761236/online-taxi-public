package com.jerry.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/21
 */
@Getter
@Setter
public class VerificationCodeDTO {

    private String passengerPhone;

    private String code;

}
