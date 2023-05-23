package com.jerry.common.response;

import javax.annotation.Resource;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * restful响应响应体统一包装，只拦截项目统一路径下的controller
 *
 * @author qijie
 * @date 2023/5/21E
 */
public class CommonJsonResponseAdvice implements ResponseBodyAdvice<Object> {

    @Resource
    private ObjectMapper mapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果使用的消息转换器的类型是JSON类型，正常来讲这条判断就能够命中
        if (JsonbHttpMessageConverter.class.isAssignableFrom(converterType)) {
            return true;
        }

        Class<?> containingClass = returnType.getContainingClass();
        // 判断controller是否有@RestController或者@ResponseBody
        if (containingClass.isAnnotationPresent(RestController.class)
            || containingClass.isAnnotationPresent(ResponseBody.class)) {
            return true;
        }

        // 判断具体方法是否有@ResponseBody
        return returnType.hasMethodAnnotation(ResponseBody.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response) {

        if (body instanceof JsonResponseWrapper) {
            return body;
        }

        JsonResponseWrapper<Object> wrapper = JsonResponseWrapper.success(body);

        // 如果是使用的StringHttpMessageConverter，重写响应头
        if (StringHttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            String returnBody;
            try {
                returnBody = mapper.writeValueAsString(wrapper);
            }
            catch (Exception e) {
                returnBody = "{'code': 500, 'message': '服务器内部错误，请联系统管理员检修'}";
            }
            return returnBody;
        }

        return wrapper;
    }

}
