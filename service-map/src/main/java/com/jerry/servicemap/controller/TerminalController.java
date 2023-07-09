package com.jerry.servicemap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.dto.resp.TerminalResp;
import com.jerry.common.dto.resp.TrSearchResp;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicemap.service.TerminalService;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@RestController
@RequestMapping("/map/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    /**
     * 添加终端
     */
    @PostMapping("/add")
    public JsonRespWrapper<TerminalResp> add(String name, String desc) {
        return terminalService.add(name, desc);
    }

    /**
     * 终端搜索
     */
    @PostMapping("/around-search")
    public JsonRespWrapper<List<TerminalResp>> aroundsearch(String center, Integer radius) {
        return terminalService.aroundSearch(center, radius);
    }

    /**
     * 轨迹查询
     */
    @PostMapping("/tr-search")
    public JsonRespWrapper<TrSearchResp> trsearch(String tid, Long starttime, Long endtime) {
        return terminalService.trSearch(tid, starttime, endtime);
    }
}
