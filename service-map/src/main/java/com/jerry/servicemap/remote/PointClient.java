package com.jerry.servicemap.remote;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jerry.common.dto.req.PointReq;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Service
public class PointClient {

    @Autowired
    private MapServicePathResolver pathResolver;

    @Autowired
    private RestTemplate restTemplate;

    public JsonRespWrapper upload(PointReq pointReq) {
        String url = pathResolver.getMapPointUploadUrl(pointReq.getTid(), pointReq.getTrId(), pointReq.getPoints());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(URI.create(url), null, String.class);
        System.out.println("上传位置响应：" + stringResponseEntity.getBody());
        return JsonRespWrapper.success();
    }

}
