package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.SysRoleResource;

import java.util.List;

/**
 * 角色-资源(SysRoleResource)表服务接口
 *
 * @author Florence
 * @since 2023/05/18
 */
public interface SysRoleResourceService extends IService<SysRoleResource> {

    boolean removeByRoleId(Short id);

    List<SysRoleResource> listByRoleId(Integer roleId);
    List<Short> listByRoleId(List<Short> roleIds);
}

