package com.jerry.servicemap.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jerry.common.dto.resp.TrackResp;
import com.jerry.common.response.JsonRespWrapper;

import net.sf.json.JSONObject;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Service
public class TrackClient {

    @Autowired
    private MapServicePathResolver pathResolver;

    @Autowired
    private RestTemplate restTemplate;

    public JsonRespWrapper add(String tid) {
        String url = pathResolver.getMapTrackAddUrl(tid);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, null, String.class);
        String body = stringResponseEntity.getBody();

        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        // 轨迹id
        String trId = data.getString("trId");
        // 轨迹名称
        String trName = "";
        if (data.has("trName")) {
            trName = data.getString("trName");
        }
        TrackResp resp = new TrackResp();
        resp.setTrId(trId);
        resp.setTrName(trName);

        return JsonRespWrapper.success(resp);
    }
}
