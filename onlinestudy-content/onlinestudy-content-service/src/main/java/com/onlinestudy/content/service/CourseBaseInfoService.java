package com.onlinestudy.content.service;

import com.onlinestudy.base.model.PageParams;
import com.onlinestudy.base.model.PageResult;
import com.onlinestudy.content.model.dto.AddCourseDto;
import com.onlinestudy.content.model.dto.CourseBaseInfoDto;
import com.onlinestudy.content.model.dto.QueryCourseParamsDto;
import com.onlinestudy.content.model.po.CourseBase;

/**
 * 课程管理Service
 */
public interface CourseBaseInfoService {

    /**
     * 课程查询
     * @param params 分页参数
     * @param queryCourseParamsDto 查询条件
     * @return
     */
    public PageResult<CourseBase> queryCourseBaseList(PageParams params, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * 新增课程
     * @param companyId 培训机构id
     * @param addCourseDto 新增课程的信息
     * @return 课程信息包括基本信息和营销信息
     */
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

}
