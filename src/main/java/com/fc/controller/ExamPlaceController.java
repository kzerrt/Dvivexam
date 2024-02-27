package com.fc.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Constants;
import com.fc.common.util.JsonVos;
import com.fc.pojo.po.ExamPlace;
import com.fc.pojo.vo.DataJsonVo;
import com.fc.pojo.vo.JsonVo;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.list.ExamPlaceVo;
import com.fc.pojo.vo.list.ProvinceVo;
import com.fc.pojo.vo.req.page.ExamPlacePageReqVo;
import com.fc.pojo.vo.req.save.ExamPlaceReqVo;
import com.fc.service.ExamPlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.ast.And;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;


/**
 * 考场表(ExamPlace)表控制层
 *
 * @author Florence
 * @since 2023/05/02
 */
@RestController
@RequestMapping("/examPlaces")
@Api(tags = "考场")
public class ExamPlaceController extends BaseController<ExamPlace, ExamPlaceReqVo>{
    /**
     * 服务对象
     */
    @Autowired
    private ExamPlaceService service;
    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(value = {Constants.Permisson.EXAM_PLACE_LIST})
    public PageJsonVo<ExamPlaceVo> list(ExamPlacePageReqVo query) {
        return JsonVos.ok(service.list(query));
    }

    @GetMapping("/regionExamPlaces")
    @ApiOperation("查询所有省份、城市、考场")
    @RequiresPermissions(value = {Constants.Permisson.EXAM_PLACE_LIST})
    public DataJsonVo<List<ProvinceVo>> listRegionExamPlaces() {
        List<ProvinceVo> vos = service.listRegionExamPlaces();
        return JsonVos.ok(vos);
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permisson.EXAM_PLACE_ADD,
            Constants.Permisson.EXAM_PLACE_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(ExamPlaceReqVo examPlaceReqVo) {
        return super.save(examPlaceReqVo);
    }

    @Override
    protected IService<ExamPlace> getService() {
        return service;
    }

    @Override
    protected Function<ExamPlaceReqVo, ExamPlace> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}


