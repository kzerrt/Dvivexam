package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.enchance.MyPage;
import com.fc.common.mapStruct.MapStructs;
import com.fc.pojo.po.ExamPlace;
import com.fc.mapper.ExamPlaceMapper;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.ExamPlaceVo;
import com.fc.pojo.vo.list.ProvinceVo;
import com.fc.pojo.vo.req.page.ExamPlacePageReqVo;
import com.fc.service.ExamPlaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 考场表(ExamPlace)表服务实现类
 *
 * @author Florence
 * @since 2023/05/02
 */
@Service
@Transactional
public class ExamPlaceServiceImpl extends ServiceImpl<ExamPlaceMapper, ExamPlace> implements ExamPlaceService {
    @Override
    @Transactional(readOnly = true)
    public PageVo<ExamPlaceVo> list(ExamPlacePageReqVo query) {
        //创造查询条件
        MyLambdaQueryWrapper<ExamPlace> wrapper = new MyLambdaQueryWrapper<>();
        String keyword = query.getKeyword();
        wrapper.like(keyword, ExamPlace::getName, ExamPlace::getAddress);
        Integer cityId = query.getCityId();
        Integer provinceId = query.getProvinceId();
        if (cityId != null && cityId > 0) {
            wrapper.eq(ExamPlace::getCityId, cityId);
        } else if (provinceId != null && provinceId > 0){
            wrapper.eq(ExamPlace::getProvinceId, provinceId);
        }
        //分页
        MyPage<ExamPlace> page = new MyPage<>(query);
        //根据条件查询
        return baseMapper.selectPage(page, wrapper).buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    public List<ProvinceVo> listRegionExamPlaces() {
        return baseMapper.selectPlaces();
    }
}

