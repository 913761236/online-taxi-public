package com.jerry.servicepay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.jerry.servicepay.mapper")
@SpringBootApplication
public class ServicePayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePayApplication.class, args);
    }

}
