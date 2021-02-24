package com.course.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/system")   //这样之后的每个地址都加了"/system"
@RestController
public class TestController {

    @RequestMapping("/test")   //访问test地址
    public String test(){
        return "success";
    }
}
