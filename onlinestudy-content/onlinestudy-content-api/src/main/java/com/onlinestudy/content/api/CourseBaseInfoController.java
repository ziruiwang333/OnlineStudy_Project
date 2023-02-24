package com.onlinestudy.content.api;

import com.onlinestudy.base.model.PageParams;
import com.onlinestudy.base.model.PageResult;
import com.onlinestudy.content.model.dto.AddCourseDto;
import com.onlinestudy.content.model.dto.CourseBaseInfoDto;
import com.onlinestudy.content.model.dto.QueryCourseParamsDto;
import com.onlinestudy.content.model.po.CourseBase;
import com.onlinestudy.content.service.CourseBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseBaseInfoController {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody QueryCourseParamsDto queryCourseParamsDto){
        //调用Service获取数据
        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParamsDto);
        return courseBasePageResult;
    }

    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody AddCourseDto addCourseDto){
        //获取当前用户所属培训机构的id
        Long companyId = 22L;
        //调用service
        CourseBaseInfoDto courseBaseInfoDto = courseBaseInfoService.createCourseBase(companyId, addCourseDto);
        return courseBaseInfoDto;
    }

}
