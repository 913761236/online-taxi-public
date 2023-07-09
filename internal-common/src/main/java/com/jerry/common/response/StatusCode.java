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

    SC_TOKEN_REFRESH_ERROR(800, "刷新token错误"),

    /**
     * 验证码错误提示：1000-1099
     */
    VERIFICATION_CODE_ERROR(1000, "验证码不正确"),

    /**
     * 用户业务提示：1200 - 1299
     */
    SC_USER_NOT_EXISTS(1200, "当前的用户不存在"),

    /**
     * 计价规则:1300-1399
     */
    PRICE_RULE_EMPTY(1300, "计价规则不存在"),

    PRICE_RULE_EXISTS(1301, "计价规则已存在，不允许添加"),

    PRICE_RULE_NOT_EDIT(1302, "计价规则没有变化"),

    PRICE_RULE_CHANGED(1303, "计价规则有变化"),

    /**
     * 地图信息：1400-1499
     */
    MAP_DISTRICT_ERROR(1400, "请求地图错误"),

    /**
     * 司机和车辆：1500-1599
     */
    DRIVER_CAR_BIND_NOT_EXISTS(1500, "司机和车辆绑定关系不存在"),

    DRIVER_NOT_EXIST(1501, "司机不存在"),

    DRIVER_CAR_BIND_EXISTS(1502, "司机和车辆绑定关系已存在，请勿重复绑定"),

    DRIVER_BIND_EXISTS(1503, "司机已经被绑定了，请勿重复绑定"),

    CAR_BIND_EXISTS(1504, "车辆已经被绑定了，请勿重复绑定"),

    CITY_DRIVER_EMPTY(1505, "当前城市没有可用的司机"),

    AVAILABLE_DRIVER_EMPTY(1506, "可用的司机为空");

    private final Integer code;

    private final String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
