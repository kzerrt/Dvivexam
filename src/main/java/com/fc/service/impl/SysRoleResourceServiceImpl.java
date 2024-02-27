package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.util.Streams;
import com.fc.pojo.po.SysRoleResource;
import com.fc.mapper.SysRoleResourceMapper;
import com.fc.service.SysRoleResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 角色-资源(SysRoleResource)表服务实现类
 *
 * @author Florence
 * @since 2023/05/18
 */
@Service
@Transactional
public class SysRoleResourceServiceImpl
        extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements SysRoleResourceService {
    @Override
    public boolean removeByRoleId(Short id) {
        return baseMapper.deleteById(id) > 0;
    }

    @Override
    public List<SysRoleResource> listByRoleId(Integer roleId) {
        MyLambdaQueryWrapper<SysRoleResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.eq(SysRoleResource::getRoleId, roleId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Short> listByRoleId(List<Short> roleIds) {
        MyLambdaQueryWrapper<SysRoleResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.in(SysRoleResource::getRoleId, roleIds);
        List<Object> objects = baseMapper.selectObjs(wrapper);
        return Streams.stream(objects, obj -> ((Integer) obj).shortValue());
    }
}

