package com.coursera.business.controller;

import com.coursera.server.domain.Chapter;
import com.coursera.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

//@RequestMapping("/system")   //这样之后的每个地址都加了"/system"
@RestController
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")   //访问chapter地址
    public List<Chapter> chapter(){
        return chapterService.list();
    }
}
