package com.jerry.servicedriver.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jerry.common.dto.resp.TerminalResp;
import com.jerry.common.dto.resp.TrackResp;
import com.jerry.common.response.JsonRespWrapper;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@FeignClient("service-amp")
public interface ServiceMapClient {

    @PostMapping("/map/terminal/add")
    JsonRespWrapper<TerminalResp> addTerminal(@RequestParam("name") String name, @RequestParam("desc") String desc);

    @PostMapping(value = "/map/track/add")
    JsonRespWrapper<TrackResp> addTrack(@RequestParam("tid") String tid);

}
