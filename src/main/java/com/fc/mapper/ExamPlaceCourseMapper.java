package com.fc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.fc.common.enchance.MyPage;
import com.fc.common.enchance.MyQueryWrapper;
import com.fc.pojo.po.ExamPlaceCourse;
import com.fc.pojo.vo.list.ExamPlaceCourseVo;
import org.apache.ibatis.annotations.Param;

/**
 * 考场课程(ExamPlaceCourse)表数据库访问层
 *
 * @author Florence
 * @since 2023/05/05
 */
public interface ExamPlaceCourseMapper extends BaseMapper<ExamPlaceCourse> {
    MyPage<ExamPlaceCourseVo> selectPageVos(MyPage<ExamPlaceCourseVo> page,
                                          @Param(Constants.WRAPPER) MyQueryWrapper<ExamPlaceCourseVo> wrapper);
}


