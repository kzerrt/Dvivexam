package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.SysRole;
import com.fc.pojo.result.CodeMsg;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.SysRoleVo;
import com.fc.pojo.vo.req.page.SysRolePageReqVo;
import com.fc.pojo.vo.req.save.SysRoleReqVo;

import java.util.List;

/**
 * 角色(SysRole)表服务接口
 *
 * @author Florence
 * @since 2023/05/18
 */
public interface SysRoleService  extends IService<SysRole> {

    PageVo<SysRoleVo> list(SysRolePageReqVo reqVo);

    List<Short> listIds(Integer userId);

    boolean saveOrUpdateRole(SysRoleReqVo reqVo);

    List<Short> resourcesByRoleId(Integer roleId);
    List<SysRole> listByRoles(List<Short> roleIds);
}

