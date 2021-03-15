package com.coursera.business.controller.admin;

import com.coursera.server.domain.Chapter;
import com.coursera.server.dto.ChapterDto;
import com.coursera.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/admin/chapter")
@RestController
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")   //访问chapter地址
    public List<ChapterDto> list(){
        return chapterService.list();
    }
}
