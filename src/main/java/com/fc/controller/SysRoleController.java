package com.fc.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Constants;
import com.fc.common.util.JsonVos;
import com.fc.common.util.Streams;
import com.fc.pojo.po.SysRole;
import com.fc.pojo.vo.DataJsonVo;
import com.fc.pojo.vo.JsonVo;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.list.SysRoleVo;
import com.fc.pojo.vo.req.page.SysRolePageReqVo;
import com.fc.pojo.vo.req.save.SysRoleReqVo;
import com.fc.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.HTML;
import java.util.List;
import java.util.function.Function;


/**
 * 角色(SysRole)表控制层
 *
 * @author Florence
 * @since 2023/05/18
 */
@RestController
@RequestMapping("/sysRoles")
@Api(tags = "用户")
public class SysRoleController extends BaseController<SysRole, SysRoleReqVo> {
    @Autowired
    private SysRoleService service;

    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(value = {Constants.Permisson.SYS_ROLE_LIST})
    public PageJsonVo<SysRoleVo> list(SysRolePageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }
    @GetMapping("/list")
    @ApiOperation("查询所有")
    @RequiresPermissions(value = {Constants.Permisson.SYS_ROLE_LIST})
    public DataJsonVo<List<SysRoleVo>> listAll() {
        return JsonVos.ok(Streams.stream(service.list(), MapStructs.INSTANCE::po2vo));
    }
    @GetMapping("/ids")
    @ApiOperation("根据用户id查询角色")
    @RequiresPermissions(value = {Constants.Permisson.SYS_ROLE_LIST})
    public DataJsonVo<List<Short>> userRoles(Integer userId) {
        return JsonVos.ok(service.listIds(userId));
    }
    @GetMapping("/resourceIds")
    @ApiOperation("根据角色id查询权限")
    @RequiresPermissions(value = {Constants.Permisson.SYS_ROLE_LIST})
    public DataJsonVo<List<Short>> resourcesByRoleId(Integer id) {
        return JsonVos.ok(service.resourcesByRoleId(id));
    }

    @Override
    public JsonVo save(SysRoleReqVo reqVo) {
        boolean flag = service.saveOrUpdateRole(reqVo);
        if (flag) {
            return JsonVos.ok();
        } else {
            return JsonVos.raise("保存失败！");
        }
    }

    @Override
    protected IService<SysRole> getService() {
        return service;
    }

    @Override
    protected Function<SysRoleReqVo, SysRole> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}


