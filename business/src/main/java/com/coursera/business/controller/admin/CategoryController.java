package com.coursera.business.controller.admin;

import com.coursera.server.dto.CategoryDto;
import com.coursera.server.dto.PageDto;
import com.coursera.server.dto.ResponseDto;
import com.coursera.server.service.CategoryService;
import com.coursera.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/admin/category")
@RestController
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    public static final String BUSINESS_NAME = "分类";

    @Resource
    private CategoryService categoryService;

    /**
     * 列表查询
     */
    @PostMapping("/all")   //访问category地址
    public ResponseDto all() {
        ResponseDto responseDto = new ResponseDto();
        List<CategoryDto> categoryDtoList = categoryService.all();
        responseDto.setContent(categoryDtoList);
        return responseDto;
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")   //访问category地址
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        categoryService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")   //访问category地址
    public ResponseDto save(@RequestBody CategoryDto categoryDto) {
        // 保存校验
        ValidatorUtil.require(categoryDto.getParent(), "父id");
        ValidatorUtil.require(categoryDto.getName(), "名称");
        ValidatorUtil.length(categoryDto.getName(), "名称", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        categoryService.save(categoryDto);
        responseDto.setContent(categoryDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")   //访问category地址
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        categoryService.delete(id);
        return responseDto;
    }
}