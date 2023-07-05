package com.jerry.apipassenger.client.vcode;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jerry.common.response.JsonRespWrapper;

/**
 * @author qijie
 * @date 2023/5/30
 */
@FeignClient("service-verification-code")
public interface VerificationCodeClient {

    @GetMapping("/verification-code/{size}")
    JsonRespWrapper<String> createCode(@PathVariable("size") int size);

}
