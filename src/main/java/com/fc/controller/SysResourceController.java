package com.fc.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Constants;
import com.fc.common.util.JsonVos;
import com.fc.common.util.Streams;
import com.fc.pojo.po.SysResource;
import com.fc.pojo.vo.DataJsonVo;
import com.fc.pojo.vo.JsonVo;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.list.SysResourceTreeVo;
import com.fc.pojo.vo.list.SysResourceVo;
import com.fc.pojo.vo.req.page.SysResourcePageReqVo;
import com.fc.pojo.vo.req.save.SysResourceReqVo;
import com.fc.service.SysResourceService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Function;


/**
 * 资源(SysResource)表控制层
 *
 * @author Florence
 * @since 2023/05/18
 */
@RestController
@RequestMapping("/sysResources")
public class SysResourceController extends BaseController<SysResource, SysResourceReqVo>{
    @Autowired
    private SysResourceService service;

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(value = {Constants.Permisson.SYS_RESOURCE_LIST})
    public PageJsonVo<SysResourceVo> list(SysResourcePageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }
    @GetMapping("/list")
    @ApiOperation("查询所有")
    @RequiresPermissions(value = {Constants.Permisson.SYS_RESOURCE_LIST})
    public DataJsonVo<List<SysResourceVo>> list() {
        return JsonVos.ok(Streams.stream(service.list(), MapStructs.INSTANCE::po2vo));
    }
    @GetMapping("/parents")
    @ApiOperation("将所有夫资源查找{目录，菜单}")
    @RequiresPermissions(value = {Constants.Permisson.SYS_RESOURCE_LIST})
    public DataJsonVo<List<SysResourceVo>> listParents() {
        return JsonVos.ok(service.listParents());
    }
    @GetMapping("/trees")
    @ApiOperation("查询所有资源【树状结构】")
    @RequiresPermissions(value = {Constants.Permisson.SYS_RESOURCE_LIST})
    public DataJsonVo<List<SysResourceTreeVo>> listTrees() {
        return JsonVos.ok(service.listTrees());
    }


    @Override
    protected IService<SysResource> getService() {
        return service;
    }

    @Override
    protected Function<SysResourceReqVo, SysResource> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}


