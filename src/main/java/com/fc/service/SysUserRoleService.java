package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.SysUserRole;

import java.util.List;

/**
 * 用户-角色(SysUserRole)表服务接口
 *
 * @author Florence
 * @since 2023/05/18
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    boolean removeByUserId(Integer id);
    List<Short> listByUserId(Integer id);
}

