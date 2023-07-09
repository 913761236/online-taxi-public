package com.jerry.servicemap.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Service
public class MapDicDistrictClient {

    @Autowired
    private MapServicePathResolver pathResolver;

    @Autowired
    private RestTemplate restTemplate;

    public String dicDistrict(String keywords) {
        String url = pathResolver.getDistrictUrl(keywords);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);

        return forEntity.getBody();
    }

}
