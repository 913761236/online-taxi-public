package com.jerry.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qijie
 * @date 2023/6/6
 */
@Configuration
public class JwtConfiguration {

    @Bean
    JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
