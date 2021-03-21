package com.coursera.business.controller.admin;

import com.coursera.server.dto.ChapterDto;
import com.coursera.server.dto.ChapterPageDto;
import com.coursera.server.dto.ResponseDto;
import com.coursera.server.service.ChapterService;
import com.coursera.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/admin/chapter")
@RestController
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    public static final String BUSINESS_NAME = "大章";

    @Resource
    private ChapterService chapterService;

    /**
     * 列表查询
     */
    @PostMapping("/list")   //访问chapter地址
    public ResponseDto list(@RequestBody ChapterPageDto chapterPageDto) {
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(chapterPageDto.getCourseId(), "课程ID");
        chapterService.list(chapterPageDto);
        responseDto.setContent(chapterPageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")   //访问chapter地址
    public ResponseDto save(@RequestBody ChapterDto chapterDto) {
        //保存校验
//前后端分离的项目，后端接口需要增加和前端一样的校验，防止被绕过前端界面，利用第三方工具如postman，直接访问后端接口
        ValidatorUtil.require(chapterDto.getName(), "名称");
        ValidatorUtil.require(chapterDto.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDto.getCourseId(), "课程ID", 1, 8);

        ResponseDto responseDto = new ResponseDto();
        chapterService.save(chapterDto);
        responseDto.setContent(chapterDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")   //访问chapter地址
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        chapterService.delete(id);
        return responseDto;
    }
}