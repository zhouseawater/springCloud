package com.seawater.mybatisPlus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhouhaishui on 2018/7/19.
 */
@RestController
@RequestMapping("/mybatisPlus/hello")
public class HelloController {

    @RequestMapping(value = "hello")
    public String hello(){
        return "hello , mybatis plus !";
    }
}
