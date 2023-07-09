package com.jerry.common.dto.resp;

import lombok.Data;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/9
 */
@Data
public class TerminalResp {

    private String tid;

    private Long carId;

    private String longitude;

    private String latitude;

}
