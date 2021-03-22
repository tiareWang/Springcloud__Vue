package com.coursera.server.service;

import com.coursera.server.util.CopyUtil;
import com.coursera.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.coursera.server.domain.Category;
import com.coursera.server.domain.CategoryExample;
import com.coursera.server.dto.CategoryDto;
import com.coursera.server.dto.PageDto;
import com.coursera.server.mapper.CategoryMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 列表查询
     */
    public List<CategoryDto> all(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        return categoryDtoList;
    }

    /**
     * 列表查询
     */
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize()); //分页功能
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CategoryDto> categoryDtoList = CopyUtil.copyList(categoryList, CategoryDto.class);
        pageDto.setList(categoryDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(CategoryDto categoryDto){
        Category category = CopyUtil.copy(categoryDto, Category.class);
        if(StringUtils.isEmpty(categoryDto.getId())){
            this.insert(category);
        } else {
            this.update(category);
        }
    }

    /**
     * 新增
     */
    private void insert(Category category){
        category.setId(UuidUtil.getShortUuid());
        categoryMapper.insert(category);
    }

    /**
     * 更新
     */
    private void update(Category category){
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 删除
     */
    public void delete(String id){
        categoryMapper.deleteByPrimaryKey(id);

    }
}