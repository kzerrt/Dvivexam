package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.enchance.MyPage;
import com.fc.common.mapStruct.MapStructs;
import com.fc.mapper.PlateRegionMapper;
import com.fc.pojo.po.PlateRegion;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.PlateRegionVo;
import com.fc.pojo.vo.list.ProvinceVo;
import com.fc.pojo.vo.req.page.CityPageReqVo;
import com.fc.pojo.vo.req.page.ProvincePageReqVo;
import com.fc.service.PlateRegionService;
import com.github.promeg.pinyinhelper.Pinyin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * (PlateRegion)表服务实现类
 *
 * @author Florence
 * @since 2023/05/01
 */
@Service
@Transactional
public class PlateRegionServiceImpl extends ServiceImpl<PlateRegionMapper, PlateRegion> implements PlateRegionService {

    @Override
    public boolean save(PlateRegion entity) {
        processPinyin(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(PlateRegion entity) {
        processPinyin(entity);
        return super.updateById(entity);
    }

    private void processPinyin(PlateRegion region) {
        String name = region.getName();
        if (name == null) return;
        region.setPinyin(Pinyin.toPinyin(name, "_"));
    }
    @Override
    @Transactional(readOnly = true)
    public PageVo<PlateRegionVo> listProvinces(ProvincePageReqVo query) {
        MyLambdaQueryWrapper<PlateRegion> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.like(query.getKeyword(), PlateRegion::getName, PlateRegion::getPlate, PlateRegion::getPinyin);
        wrapper.eq(PlateRegion::getProvinceId, 0);

        return baseMapper.selectPage(new MyPage<>(query),wrapper).buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public PageVo<PlateRegionVo> listCities(CityPageReqVo query) {
        MyLambdaQueryWrapper<PlateRegion> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.like(query.getKeyword(), PlateRegion::getName, PlateRegion::getPlate, PlateRegion::getPinyin);
        Integer provinceId = query.getProvinceId();
        if(provinceId != null && provinceId > 0) {
            wrapper.eq(PlateRegion::getProvinceId, provinceId);
        } else {
            wrapper.ne(PlateRegion::getProvinceId, 0);
        }
        return baseMapper.selectPage(new MyPage<>(query),wrapper).buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlateRegion> listAllProvince() {
        MyLambdaQueryWrapper<PlateRegion> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.eq(PlateRegion::getProvinceId, 0);
        wrapper.orderByAsc(PlateRegion::getPinyin);
        return baseMapper.selectList(wrapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProvinceVo> listRegions() {
        return baseMapper.selectRegions();
    }
}

