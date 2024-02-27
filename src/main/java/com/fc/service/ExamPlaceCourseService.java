package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.ExamPlaceCourse;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.ExamPlaceCourseVo;
import com.fc.pojo.vo.req.page.ExamPlaceCoursePageReqVo;

/**
 * 考场课程(ExamPlaceCourse)表服务接口
 *
 * @author Florence
 * @since 2023/05/05
 */
public interface ExamPlaceCourseService extends IService<ExamPlaceCourse> {

    PageVo<ExamPlaceCourseVo> list(ExamPlaceCoursePageReqVo query);
}

