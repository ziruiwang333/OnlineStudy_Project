package com.onlinestudy.content.service.impl;

import com.onlinestudy.content.mapper.CourseCategoryMapper;
import com.onlinestudy.content.model.dto.CourseCategoryTreeDto;
import com.onlinestudy.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Describe:
 * @Author: Zirui
 * @Date: 2023/2/15
 */

@Slf4j
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        //得到根节点下所有子节点
        List<CourseCategoryTreeDto> courseCategoryTreeDtoList = courseCategoryMapper.selectTreeNodes(id);
        //定义一个List作为最终返回的数据
        List<CourseCategoryTreeDto> resultList = new ArrayList<>();
        //为方便找子节点的父节点，定义一个map
        Map<String, CourseCategoryTreeDto> nodeMap = new HashMap<>();
        //将数据封装到List中，只包括当前根节点的直接下属节点
        courseCategoryTreeDtoList.stream().forEach(item -> {
            nodeMap.put(item.getId(), item);
            if(item.getParentid().equals(id)){
                resultList.add(item);
            }
            //找到该节点的父节点
            String parentid = item.getParentid();
            CourseCategoryTreeDto parentNode = nodeMap.get(parentid);
            if(parentNode != null){
                //找到子节点，放到它的父节点的childrenTreeNodes属性中
                if(parentNode.getChildrenTreeNodes() == null){
                    parentNode.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                parentNode.getChildrenTreeNodes().add(item);
            }
        });
        return resultList;
    }
}
