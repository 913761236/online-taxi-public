package com.jerry.servicemap.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jerry.common.dto.resp.DirectionResp;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 三方地图服务
 *
 * @author qijie
 * @date 2023/6/7
 */
@Slf4j
@Service
public class MapDirectionClient {

    @Autowired
    private MapServicePathResolver pathResolver;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResp direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {

        String url = pathResolver.getDirectionDrivingPath(depLongitude, depLatitude, destLongitude, destLatitude);

        // 调用高德接口
        log.info("高德地图：路径规划，请求信息：" + url);
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(url, String.class);
        String directionString = directionEntity.getBody();
        log.info("高德地图：路径规划，返回信息：" + directionString);
        // 解析高德接口响应的数据
        return parseDirectionEntity(directionString);
    }

    private DirectionResp parseDirectionEntity(String directionString) {
        DirectionResp directionResp = null;
        try {
            // 最外层
            JSONObject result = mapper.readValue(directionString, JSONObject.class);
            if (result.has(AMapConstants.STATUS)) {
                int status = result.getInt(AMapConstants.STATUS);
                if (status == 1) {
                    if (result.has(AMapConstants.ROUTE)) {
                        JSONObject routeObject = result.getJSONObject(AMapConstants.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(AMapConstants.PATHS);
                        JSONObject pathObject = pathsArray.getJSONObject(0);
                        directionResp = new DirectionResp();

                        if (pathObject.has(AMapConstants.DISTANCE)) {
                            int distance = pathObject.getInt(AMapConstants.DISTANCE);
                            directionResp.setDistance(distance);
                        }
                        if (pathObject.has(AMapConstants.DURATION)) {
                            int duration = pathObject.getInt(AMapConstants.DURATION);
                            directionResp.setDuration(duration);
                        }
                    }
                }
            }

        }
        catch (Exception ignored) {

        }
        return directionResp;
    }
}
