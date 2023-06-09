package com.jerry.apipassenger.remote.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jerry.common.dto.PassengerUser;
import com.jerry.common.dto.VerificationCodeDTO;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @PostMapping("/user")
    JsonRespWrapper<PassengerUser> loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);

    @GetMapping("/user/{passengerPhone}")
    JsonRespWrapper<PassengerUser> getUserByPhone(@PathVariable("passengerPhone") String passengerPhone);
}
