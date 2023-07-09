package com.jerry.servicemap.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 高德地图服务配置类
 *
 * @author qijie
 * @date 2023/6/7
 */
@Component
@ConfigurationProperties("amap")
public class MapProperties {

    /**
     * 服务地址
     */
    private String serverAddress;

    /**
     * 三方认证key
     */
    private String key;

    /**
     * 三方认证secret
     */
    private String sid;

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
