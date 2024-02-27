package com.fc.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.JsonVos;
import com.fc.common.util.Streams;
import com.fc.pojo.po.PlateRegion;
import com.fc.pojo.result.R;
import com.fc.pojo.vo.DataJsonVo;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.PlateRegionVo;
import com.fc.pojo.vo.req.page.CityPageReqVo;
import com.fc.pojo.vo.req.page.ProvincePageReqVo;
import com.fc.pojo.vo.req.save.PlateRegionReqVo;
import com.fc.service.PlateRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;


/**
 * (PlateRegion)表控制层
 *
 * @author Florence
 * @since 2023/05/01
 */
@RestController
@RequestMapping("/plateRegions")
@Api(tags = "城市，省份")
public class PlateRegionController extends BaseController<PlateRegion, PlateRegionReqVo> {
    /**
     * 服务对象
     */
    @Autowired
    private PlateRegionService service;

    @GetMapping("/province")
    @ApiOperation("分页查询省份")
    public PageJsonVo<PlateRegionVo> listProvince(ProvincePageReqVo query) {
        PageVo<PlateRegionVo> vo = service.listProvinces(query);
        return JsonVos.ok(vo);
    }
    @GetMapping("/province/list")
    @ApiOperation("查询所有城市")
    public DataJsonVo<List<PlateRegionVo>> listProvince() {
        List<PlateRegion> plateRegions = service.listAllProvince();
        return JsonVos.ok(Streams.stream(plateRegions, MapStructs.INSTANCE::po2vo));
    }
    @GetMapping("/cities")
    @ApiOperation("分页查询城市")
    public PageJsonVo<PlateRegionVo> listCities(CityPageReqVo query) {
        PageVo<PlateRegionVo> vo = service.listCities(query);
        return JsonVos.ok(vo);
    }
    @GetMapping("/regions")
    public R listRegions() {
        return null;
    }
    @Override
    protected IService<PlateRegion> getService() {
        return service;
    }

    @Override
    protected Function<PlateRegionReqVo, PlateRegion> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}


