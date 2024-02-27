package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.util.Streams;
import com.fc.pojo.po.SysUserRole;
import com.fc.mapper.SysUserRoleMapper;
import com.fc.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户-角色(SysUserRole)表服务实现类
 *
 * @author Florence
 * @since 2023/05/18
 */
@Service
@Transactional
public class SysUserRoleServiceImpl
        extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {
    @Override
    public boolean removeByUserId(Integer id) {
        int i = baseMapper.deleteById(id);
        return i > 0;
    }

    @Override
    public List<Short> listByUserId(Integer id) {
        MyLambdaQueryWrapper<SysUserRole> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.select(SysUserRole::getRoleId);
        wrapper.eq(SysUserRole::getUserId, id);
        List<Object> objects = baseMapper.selectObjs(wrapper);
        return Streams.stream(objects,obj -> ((Integer) obj).shortValue());
    }
}

