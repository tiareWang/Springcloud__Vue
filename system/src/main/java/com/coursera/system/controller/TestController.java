package com.coursera.system.controller;

import com.coursera.server.domain.Test;
import com.coursera.server.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//@RequestMapping("/system")   //这样之后的每个地址都加了"/system"
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/test")   //访问test地址
    public List<Test> test(){
        return testService.list();
    }
}
