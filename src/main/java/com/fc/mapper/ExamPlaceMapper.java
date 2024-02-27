package com.fc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.pojo.po.ExamPlace;
import com.fc.pojo.vo.list.ProvinceVo;

import java.util.List;

/**
 * 考场表(ExamPlace)表数据库访问层
 *
 * @author Florence
 * @since 2023/05/02
 */
public interface ExamPlaceMapper extends BaseMapper<ExamPlace> {
    List<ProvinceVo> selectPlaces();
}


