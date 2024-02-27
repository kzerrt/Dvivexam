package com.fc.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Constants;
import com.fc.common.util.JsonVos;
import com.fc.common.util.Streams;
import com.fc.pojo.po.DictType;
import com.fc.pojo.vo.DataJsonVo;
import com.fc.pojo.vo.JsonVo;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.DictTypeVo;
import com.fc.pojo.vo.req.page.DictTypePageReqVo;
import com.fc.pojo.vo.req.save.DictTypeReqVo;
import com.fc.service.DictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Function;

/**
 * (DictType)表控制层
 *
 * @author florence
 * @since 2023/04/27
 */
@RestController
@RequestMapping("/dictTypes")
@Api(tags = "数据字典类型")
public class DictTypeController extends BaseController<DictType, DictTypeReqVo>{
    /**
     * 服务对象
     */
    @Resource
    private DictTypeService service;

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(value = {Constants.Permisson.DICT_TYPE_LIST})
    public PageJsonVo<DictTypeVo> list(DictTypePageReqVo query) {
        PageVo<DictTypeVo> vo = service.list(query);
        return JsonVos.ok(vo);
    }
    @GetMapping("/list")
    @ApiOperation("查询所有")
    @RequiresPermissions(value = {Constants.Permisson.DICT_TYPE_LIST})
    public DataJsonVo<List<DictTypeVo>> list() {
        List<DictType> list = service.list();
        return JsonVos.ok(Streams.stream(list, MapStructs.INSTANCE::po2vo));
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permisson.DICT_TYPE_ADD,
            Constants.Permisson.DICT_TYPE_UPDATE
    },logical = Logical.AND)
    public JsonVo save(DictTypeReqVo dictTypeReqVo) {
        return super.save(dictTypeReqVo);
    }

    @Override
    protected IService<DictType> getService() {
        return service;
    }

    @Override
    protected Function<DictTypeReqVo, DictType> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}


