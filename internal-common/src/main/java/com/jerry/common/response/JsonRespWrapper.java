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
public class JsonRespWrapper<T> {

    private Integer code;

    private String message;

    private T data;

    public JsonRespWrapper() {}

    public JsonRespWrapper(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonRespWrapper(StatusCode sc) {
        this.code = sc.getCode();
        this.message = sc.getMessage();
    }

    public static JsonRespWrapper<String> success() {
        JsonRespWrapper<String> wrapper = new JsonRespWrapper<>(StatusCode.SC_OK);
        wrapper.data = "";
        return wrapper;
    }

    public static <T> JsonRespWrapper<T> success(T data) {
        JsonRespWrapper<T> wrapper = new JsonRespWrapper<>(StatusCode.SC_OK);
        wrapper.data = data;
        return wrapper;
    }

    public static <T> JsonRespWrapper<T> failure(StatusCode code, T data) {
        JsonRespWrapper<T> wrapper = new JsonRespWrapper<>();
        wrapper.setCode(code.getCode());
        wrapper.setMessage(code.getMessage());
        wrapper.data = data;
        return wrapper;
    }

    public static JsonRespWrapper<String> failure(StatusCode code) {
        JsonRespWrapper<String> wrapper = new JsonRespWrapper<>();
        wrapper.setCode(code.getCode());
        wrapper.setMessage(code.getMessage());
        return wrapper;
    }
}
