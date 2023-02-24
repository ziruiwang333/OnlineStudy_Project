package com.onlinestudy.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.onlinestudy.base.model.PageParams;
import com.onlinestudy.base.model.PageResult;
import com.onlinestudy.content.mapper.CourseBaseMapper;
import com.onlinestudy.content.mapper.CourseCategoryMapper;
import com.onlinestudy.content.mapper.CourseMarketMapper;
import com.onlinestudy.content.model.dto.AddCourseDto;
import com.onlinestudy.content.model.dto.CourseBaseInfoDto;
import com.onlinestudy.content.model.dto.QueryCourseParamsDto;
import com.onlinestudy.content.model.po.CourseBase;
import com.onlinestudy.content.model.po.CourseCategory;
import com.onlinestudy.content.model.po.CourseMarket;
import com.onlinestudy.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CourseBaseInfoServiceImpl implements CourseBaseInfoService {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //拼接查询条件
        //根据课程名称模糊查询
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()), courseBase -> courseBase.getName(), queryCourseParamsDto.getCourseName());
        //根据课程审核状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()), courseBase -> courseBase.getAuditStatus(), queryCourseParamsDto.getAuditStatus());
        //根据课程发布状态
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()), (CourseBase courseBase) -> courseBase.getStatus(), queryCourseParamsDto.getPublishStatus());

        //分页参数
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);

        List<CourseBase> records = pageResult.getRecords();
        long total = pageResult.getTotal();

        //准备返回数据
        PageResult<CourseBase> courseBasePageResult = new PageResult<CourseBase>(records, total, pageParams.getPageNo(), pageParams.getPageSize());
        return courseBasePageResult;
    }

    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto) {
        //对数据进行封装，调用mapper进行数据持久化
        //向课程基本信息表插入记录
        CourseBase courseBase = new CourseBase();
        BeanUtils.copyProperties(addCourseDto, courseBase);
        //设置机构id, 创建时间, 审核状态， 发布状态
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        courseBase.setAuditStatus("202002");
        courseBase.setStatus("203001");
        int insertResult1 = courseBaseMapper.insert(courseBase);
        //获取课程id
        Long courseId = courseBase.getId();

        //向课程营销表插入一条记录
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(addCourseDto, courseMarket);
        courseMarket.setId(courseId);
        //若为收费课程，则需校验价格
        String charge = addCourseDto.getCharge();
        if("201001".equals(charge)){
            if(courseMarket.getPrice() == null || courseMarket.getPrice().floatValue() <= 0){
                throw new RuntimeException("收费课程价格不能为空或非法值");
            }
        }
        int insertResult2 = courseMarketMapper.insert(courseMarket);
        if (insertResult1 <= 0 || insertResult2 <= 0){
            throw new RuntimeException("添加课程不成功");
        }
        //组装要返回的结果
        return getCourseBaseInfo(courseId);
    }

    /**
     * 根据课程id查询课程基本信息和营销信息
     * @param courseId 课程id
     * @return 课程信息
     */
    public CourseBaseInfoDto getCourseBaseInfo(Long courseId){
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase, courseBaseInfoDto);
        BeanUtils.copyProperties(courseMarket, courseBaseInfoDto);
        //根据课程id查询分类名称 mt大分类编号， st小分类编号
        String mt = courseBase.getMt();
        String st = courseBase.getSt();

        CourseCategory mtCategory = courseCategoryMapper.selectById(mt);
        CourseCategory stCategory = courseCategoryMapper.selectById(st);

        if(mtCategory != null){
            //大分类名称
            String mtName = mtCategory.getName();
            courseBaseInfoDto.setMtName(mtName);
        }
        if(stCategory != null){
            String stName = stCategory.getName();
            courseBaseInfoDto.setStName(stName);
        }
        return courseBaseInfoDto;
    }

}
