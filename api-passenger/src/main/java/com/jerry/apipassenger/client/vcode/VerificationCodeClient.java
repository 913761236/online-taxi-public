package com.jerry.apipassenger.client.vcode;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jerry.common.response.JsonResponseWrapper;

/**
 * @author qijie
 * @date 2023/5/30
 */
@FeignClient("service-verification-code")
public interface VerificationCodeClient {

    @GetMapping("verification-cde/{size}")
    JsonResponseWrapper<String> createCode(@PathVariable("size") int size);

}
