package com.jerry.servicemap.remote;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jerry.common.dto.resp.TerminalResp;
import com.jerry.common.dto.resp.TrSearchResp;
import com.jerry.common.response.JsonRespWrapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Service
public class TerminalClient {

    @Autowired
    private MapServicePathResolver pathResolver;

    @Autowired
    private RestTemplate restTemplate;

    public JsonRespWrapper<TerminalResp> add(String name, String desc) {
        String url = pathResolver.getMapTerminalAddUrl(name, desc);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, null, String.class);
        String body = stringResponseEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String tid = data.getString("tid");

        TerminalResp resp = new TerminalResp();
        resp.setTid(tid);

        return JsonRespWrapper.success(resp);
    }

    public JsonRespWrapper<List<TerminalResp>> aroundSearch(String center, Integer radius) {
        String url = pathResolver.getMapTerminalAroundSearchUrl(center, radius);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, null, String.class);

        // 解析终端搜索结果
        String body = stringResponseEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");

        List<TerminalResp> terminalResponseList = new ArrayList<>();

        JSONArray results = data.getJSONArray("results");
        for (int i = 0; i < results.size(); i++) {
            TerminalResp terminalResponse = new TerminalResp();

            JSONObject jsonObject = results.getJSONObject(i);
            // desc是carId，
            String desc = jsonObject.getString("desc");
            Long carId = Long.parseLong(desc);
            String tid = jsonObject.getString("tid");

            JSONObject location = jsonObject.getJSONObject("location");
            String longitude = location.getString("longitude");
            String latitude = location.getString("latitude");

            terminalResponse.setCarId(carId);
            terminalResponse.setTid(tid);
            terminalResponse.setLongitude(longitude);
            terminalResponse.setLatitude(latitude);

            terminalResponseList.add(terminalResponse);
        }

        return JsonRespWrapper.success(terminalResponseList);
    }

    public JsonRespWrapper<TrSearchResp> trSearch(String tid, Long startTime, Long endTime) {
        String url = pathResolver.getMapTerminalTrSearchUrl(tid, startTime, endTime);

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);

        JSONObject result = JSONObject.fromObject(forEntity.getBody());
        JSONObject data = result.getJSONObject("data");
        int counts = data.getInt("counts");
        if (counts == 0) {
            return null;
        }
        JSONArray tracks = data.getJSONArray("tracks");
        long driveMile = 0L;
        long driveTime = 0L;
        for (int i = 0; i < tracks.size(); i++) {
            JSONObject jsonObject = tracks.getJSONObject(i);

            long distance = jsonObject.getLong("distance");
            driveMile = driveMile + distance;

            long time = jsonObject.getLong("time");
            time = time / (1000 * 60);
            driveTime = driveTime + time;
        }
        TrSearchResp resp = new TrSearchResp();
        resp.setDriveMile(driveMile);
        resp.setDriveTime(driveTime);
        return JsonRespWrapper.success(resp);
    }
}
