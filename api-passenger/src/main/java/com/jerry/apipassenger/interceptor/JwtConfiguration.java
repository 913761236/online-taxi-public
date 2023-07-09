package com.jerry.apipassenger.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qijie
 * @date 2023/6/6
 */
@Configuration
public class JwtConfiguration implements WebMvcConfigurer {

    @Bean
    JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
            // 拦截的路径
            .addPathPatterns("/**")
            // 不拦截的路径
            .excludePathPatterns("/verification-code")
            .excludePathPatterns("/verification-code-check")
            .excludePathPatterns("/token/**")
            .excludePathPatterns("/error");

    }
}
