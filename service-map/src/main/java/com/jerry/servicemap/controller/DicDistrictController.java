package com.jerry.servicemap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jerry.common.response.JsonRespWrapper;
import com.jerry.servicemap.service.DicDistrictService;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@RestController
@RequestMapping("/map")
public class DicDistrictController {

    @Autowired
    private DicDistrictService dicDistrictService;

    @GetMapping("/district")
    public JsonRespWrapper initDicDistrict(@RequestParam("keywords") String keywords) {
        return dicDistrictService.initDicDistrict(keywords);
    }

}
