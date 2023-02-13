package com.onlinestudy;

import com.onlinestudy.base.model.PageParams;
import com.onlinestudy.base.model.PageResult;
import com.onlinestudy.content.mapper.CourseBaseMapper;
import com.onlinestudy.content.model.dto.QueryCourseParamsDto;
import com.onlinestudy.content.model.po.CourseBase;
import com.onlinestudy.content.service.CourseBaseInfoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlinestudyContentServiceApplicationTests {

	@Autowired
	CourseBaseMapper courseBaseMapper;

	@Autowired
	CourseBaseInfoService courseBaseInfoService;

	@Test
	void testCourseBaseMapper() {
		CourseBase courseBase = courseBaseMapper.selectById(22);
		System.out.println(courseBase);
	}

	@Test
	void testCourseBaseInfoService(){
		PageParams pageParams = new PageParams();
		PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, new QueryCourseParamsDto());
		System.out.println(courseBasePageResult);
	}

}
