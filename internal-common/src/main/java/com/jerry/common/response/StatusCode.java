package com.jerry.common.response;

import lombok.Getter;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/22
 */
@Getter
public enum StatusCode {

    SC_ACCESS_ANONYMOUS(401, "匿名用户访问异常"),

    SC_ACCESS_DENIED(403, "无访问权限，请联系管理员授权"),

    SC_NOT_FOUND(404, "请求的资源不存在"),

    SC_INTERNAL_ERROR(500, "系统内部异常，请稍后重试"),

    SC_OK(200, ""),

    /**
     * 用户业务提示：1200 - 1299
     */
    SC_USER_NOT_EXISTS(1200, "当前的用户不存在");

    private final Integer code;

    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
