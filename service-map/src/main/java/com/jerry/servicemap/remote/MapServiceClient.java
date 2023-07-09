package com.jerry.servicemap.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jerry.common.dto.resp.MapServiceResp;
import com.jerry.common.response.JsonRespWrapper;

import net.sf.json.JSONObject;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Service
public class MapServiceClient {

    @Autowired
    private MapServicePathResolver pathResolver;

    @Autowired
    private RestTemplate restTemplate;

    public JsonRespWrapper add(String name) {
        String url = pathResolver.getMapServiceAddUrl(name);
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url, null, String.class);
        String body = forEntity.getBody();

        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String sid = data.getString("sid");
        MapServiceResp resp = new MapServiceResp();
        resp.setSid(sid);
        return JsonRespWrapper.success(resp);
    }
}
