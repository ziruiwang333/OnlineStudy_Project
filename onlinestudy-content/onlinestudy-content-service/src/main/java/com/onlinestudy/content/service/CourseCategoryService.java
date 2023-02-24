package com.onlinestudy.content.service;

import com.onlinestudy.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @Describe: 课程分类操作相关的Service
 * @Author: Zirui
 * @Date: 2023/2/15
 */
public interface CourseCategoryService {

    /**
     *
     * @param id
     * @return
     */
    List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
