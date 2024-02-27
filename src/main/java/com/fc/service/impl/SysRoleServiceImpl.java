package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.enchance.MyPage;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Streams;
import com.fc.common.util.Strings;
import com.fc.mapper.SysUserRoleMapper;
import com.fc.pojo.po.*;
import com.fc.mapper.SysRoleMapper;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.SysRoleVo;
import com.fc.pojo.vo.req.page.SysRolePageReqVo;
import com.fc.pojo.vo.req.save.SysRoleReqVo;
import com.fc.service.SysRoleResourceService;
import com.fc.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 角色(SysRole)表服务实现类
 *
 * @author Florence
 * @since 2023/05/18
 */
@Service
@Transactional
public class SysRoleServiceImpl
        extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleResourceService roleResourceService;
    @Override
    @Transactional(readOnly = true)
    public PageVo<SysRoleVo> list(SysRolePageReqVo reqVo) {
        //创造查询条件
        MyLambdaQueryWrapper<SysRole> wrapper = new MyLambdaQueryWrapper<>();
        String keyword = reqVo.getKeyword();
        wrapper.like(keyword, SysRole::getName);
        //分页
        MyPage<SysRole> page = new MyPage<>(reqVo);
        //根据条件查询
        return baseMapper.selectPage(page, wrapper).buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    public List<Short> listIds(Integer userId) {
        if (userId == null || userId <= 0) return null;
        MyLambdaQueryWrapper<SysUserRole> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.select(SysUserRole::getRoleId);
        wrapper.eq(SysUserRole::getUserId, userId);
        List<Object> objects = userRoleMapper.selectObjs(wrapper);
        return Streams.stream(objects, (id) -> ((Integer) id).shortValue());
    }

    @Override
    public boolean saveOrUpdateRole(SysRoleReqVo reqVo) {
        SysRole sysRole = MapStructs.INSTANCE.reqVo2po(reqVo);
        Short id = reqVo.getId();
        String roleIds = reqVo.getResourceIds();
        if (!saveOrUpdate(sysRole)) return false;
        if (id == null) {// 用户添加
            id = sysRole.getId();
        } else {// 用户编辑
            boolean flag = roleResourceService.removeByRoleId(id);// 删除用户角色信息
            if(!flag) return false;
        }
        if (Strings.isEmpty(roleIds)) return true;

        List<SysRoleResource> list = new ArrayList<>();
        String[] split = roleIds.split(",");
        for (String resourceId : split) {
            SysRoleResource res = new SysRoleResource();
            res.setRoleId(id);
            res.setResourceId(Short.valueOf(resourceId));
            list.add(res);
        }
        return roleResourceService.saveBatch(list);
    }

    @Override
    public List<Short> resourcesByRoleId(Integer roleId) {
        List<SysRoleResource> arrays = roleResourceService.listByRoleId(roleId);
        List<Short> list = new LinkedList<>();
        for (SysRoleResource array : arrays) {
            list.add(array.getResourceId());
        }
        return list;
    }

    @Override
    public List<SysRole> listByRoles(List<Short> roleIds) {
        MyLambdaQueryWrapper<SysRole> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.in(SysRole::getId, roleIds);
        return baseMapper.selectList(wrapper);
    }
}

