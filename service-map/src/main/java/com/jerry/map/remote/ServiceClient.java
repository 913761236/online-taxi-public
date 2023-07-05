package com.jerry.map.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.common.response.JsonRespWrapper;
import com.jerry.map.configuration.MapProperties;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Service
public class ServiceClient {

    @Autowired
    private MapProperties properties;

    // @Autowired
    // private RestTemplate restTemplate;

    public JsonRespWrapper add(String name) {
        String url = properties.getServerAddress() + "?key=" + properties.getKey() + "&name=" + name;
        // ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        // String body = forEntity.getBody();
        return null;
    }
}
