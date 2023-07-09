package com.jerry.servicemap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.common.dto.resp.TerminalResp;
import com.jerry.common.dto.resp.TrSearchResp;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicemap.remote.TerminalClient;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/9
 */
@Service
public class TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    public JsonRespWrapper<TerminalResp> add(String name, String desc) {
        return terminalClient.add(name, desc);
    }

    public JsonRespWrapper<List<TerminalResp>> aroundSearch(String center, Integer radius) {
        return terminalClient.aroundSearch(center, radius);
    }

    public JsonRespWrapper<TrSearchResp> trSearch(String tid, Long startTime, Long endTime) {
        return terminalClient.trSearch(tid, startTime, endTime);
    }
}
