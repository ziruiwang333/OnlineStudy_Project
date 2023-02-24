package com.onlinestudy.content.model.dto;

import com.onlinestudy.content.model.po.CourseCategory;
import lombok.Data;

import java.util.List;

/**
 * @Describe:
 * @Author: Zirui
 * @Date: 2023/2/15
 */

@Data
public class CourseCategoryTreeDto extends CourseCategory {
    private List<CourseCategoryTreeDto> childrenTreeNodes;
}
