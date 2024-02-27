package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.ExamPlace;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.ExamPlaceVo;
import com.fc.pojo.vo.list.ProvinceVo;
import com.fc.pojo.vo.req.page.ExamPlacePageReqVo;

import java.util.List;

/**
 * 考场表(ExamPlace)表服务接口
 *
 * @author Florence
 * @since 2023/05/02
 */
public interface ExamPlaceService extends IService<ExamPlace> {

    PageVo<ExamPlaceVo> list(ExamPlacePageReqVo query);

    // 查询所有考场、省份、城市
    List<ProvinceVo> listRegionExamPlaces();
}

