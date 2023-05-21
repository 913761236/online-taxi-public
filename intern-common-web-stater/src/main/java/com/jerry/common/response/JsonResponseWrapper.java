package com.jerry.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/21
 */
@Getter
@Setter
public class JsonResponseWrapper<T> {

    private Integer code;

    private String message;

    private T data;

    public JsonResponseWrapper() {}

    public JsonResponseWrapper(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResponseWrapper(StatusCode sc) {
        this.code = sc.getCode();
        this.message = sc.getMessage();
    }

    public static <T> JsonResponseWrapper<T> success(T data) {
        JsonResponseWrapper<T> wrapper = new JsonResponseWrapper<>(StatusCode.SC_OK);
        wrapper.data = data;
        return wrapper;
    }

    public static <T> JsonResponseWrapper<T> failure(T data) {
        JsonResponseWrapper<T> wrapper = new JsonResponseWrapper<>();
        wrapper.data = data;
        return wrapper;
    }
}