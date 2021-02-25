package com.course.system.controller;

import com.course.system.domain.Test;
import com.course.system.service.TestService;
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
