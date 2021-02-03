package com.study.sportplay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//命令所有的方法返回字符串
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "ok";
    }
}
