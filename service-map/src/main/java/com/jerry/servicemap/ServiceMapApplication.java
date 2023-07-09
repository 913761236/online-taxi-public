package com.jerry.servicemap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author qijie
 * @date 2023/6/6
 */
@MapperScan("com.jerry.servicemap.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMapApplication.class, args);
    }
}
