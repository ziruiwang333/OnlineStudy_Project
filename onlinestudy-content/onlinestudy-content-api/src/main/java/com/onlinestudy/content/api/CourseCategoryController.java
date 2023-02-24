package com.onlinestudy.content.api;

import com.onlinestudy.content.model.dto.CourseCategoryTreeDto;
import com.onlinestudy.content.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Describe:
 * @Author: Zirui
 * @Date: 2023/2/15
 */
@RestController
public class CourseCategoryController {

    @Autowired
    CourseCategoryService courseCategoryService;

    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes(){
        List<CourseCategoryTreeDto> courseCategoryTreeDtoList = courseCategoryService.queryTreeNodes("1");
        return courseCategoryTreeDtoList;
    }
}
