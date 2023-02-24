package com.onlinestudy.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onlinestudy.content.model.dto.CourseCategoryTreeDto;
import com.onlinestudy.content.model.po.CourseCategory;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
    List<CourseCategoryTreeDto> selectTreeNodes(String id);
}
