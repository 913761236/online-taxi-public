package com.jerry.servicedriver;

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
@MapperScan("com.jerry.servicedriver.mapper")
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceDriverUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceDriverUserApplication.class, args);
    }

}
