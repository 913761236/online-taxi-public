package com.jerry.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @author qijie
 * @date 2023/5/21
 */
@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public String numberCode(@PathVariable("size") int size) {
        return "123321123";
    }
}
