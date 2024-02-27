package com.fc.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Constants;
import com.fc.common.util.JsonVos;
import com.fc.pojo.po.DictItem;
import com.fc.pojo.vo.JsonVo;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.list.DictItemVo;
import com.fc.pojo.vo.req.page.DictItemPageReqVo;
import com.fc.pojo.vo.req.save.DictItemReqVo;
import com.fc.service.DictItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.function.Function;

/**
 * (DictItem)表控制层
 *
 * @author florence
 * @since 2023/04/27
 */
@RestController
@RequestMapping("/dictItems")
@Api(tags = "数据字典条目")
public class DictItemController extends BaseController<DictItem, DictItemReqVo>{
    /**
     * 服务对象
     */
    @Resource
    private DictItemService service;
    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(value = {Constants.Permisson.DICT_ITEM_LIST})
    public PageJsonVo<DictItemVo> list(DictItemPageReqVo query) {
        return JsonVos.ok(service.list(query));
    }

    @Override
    @RequiresPermissions(value = {
            Constants.Permisson.DICT_ITEM_ADD,
            Constants.Permisson.DICT_ITEM_UPDATE
    }, logical = Logical.AND)
    public JsonVo save(DictItemReqVo dictItemReqVo) {
        return super.save(dictItemReqVo);
    }

    @Override
    protected IService<DictItem> getService() {
        return service;
    }

    @Override
    protected Function<DictItemReqVo, DictItem> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}


