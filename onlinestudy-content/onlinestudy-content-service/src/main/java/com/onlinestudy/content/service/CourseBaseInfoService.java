package com.onlinestudy.content.service;

import com.onlinestudy.base.model.PageParams;
import com.onlinestudy.base.model.PageResult;
import com.onlinestudy.content.model.dto.QueryCourseParamsDto;
import com.onlinestudy.content.model.po.CourseBase;
import org.springframework.web.bind.annotation.RequestBody;

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

}
