package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.PlateRegion;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.PlateRegionVo;
import com.fc.pojo.vo.list.ProvinceVo;
import com.fc.pojo.vo.req.page.CityPageReqVo;
import com.fc.pojo.vo.req.page.ProvincePageReqVo;

import java.util.List;

/**
 * (PlateRegion)表服务接口
 *
 * @author Florence
 * @since 2023/05/01
 */
public interface PlateRegionService extends IService<PlateRegion> {

    PageVo<PlateRegionVo> listProvinces(ProvincePageReqVo query);

    PageVo<PlateRegionVo> listCities(CityPageReqVo query);

    List<PlateRegion> listAllProvince();

    List<ProvinceVo> listRegions();
}

