package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.cache.Caches;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.enchance.MyPage;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Constants;
import com.fc.common.util.JsonVos;
import com.fc.common.util.Strings;
import com.fc.pojo.dto.SysUserDto;
import com.fc.pojo.po.SysResource;
import com.fc.pojo.po.SysRole;
import com.fc.pojo.po.SysUser;
import com.fc.mapper.SysUserMapper;
import com.fc.pojo.po.SysUserRole;
import com.fc.pojo.result.CodeMsg;
import com.fc.pojo.vo.LoginVo;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.SysUserVo;
import com.fc.pojo.vo.req.LoginReqVo;
import com.fc.pojo.vo.req.page.SysUserPageReqVo;
import com.fc.pojo.vo.req.save.SysUserReqVo;
import com.fc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.schema.Maps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户（可以登录后台系统的）(SysUser)表服务实现类
 *
 * @author Florence
 * @since 2023/05/18
 */
@Service
@Transactional
public class SysUserServiceImpl
        extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserRoleService service;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysRoleResourceService roleResourceService;
    @Autowired
    private SysResourceService resourceService;

    @Override
    @Transactional(readOnly = true)
    public PageVo<SysUserVo> list(SysUserPageReqVo reqVo) {
        //创造查询条件
        MyLambdaQueryWrapper<SysUser> wrapper = new MyLambdaQueryWrapper<>();
        String keyword = reqVo.getKeyword();
        wrapper.like(keyword, SysUser::getUsername, SysUser::getNickname);
        //分页
        MyPage<SysUser> page = new MyPage<>(reqVo);
        //根据条件查询
        return baseMapper.selectPage(page, wrapper).buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    public boolean saveOrUpdateUser(SysUserReqVo reqVo) {
        SysUser sysUser = MapStructs.INSTANCE.reqVo2po(reqVo);
        Integer id = reqVo.getId();
        String roleIds = reqVo.getRoleIds();
        if (!saveOrUpdate(sysUser)) return false;
        if (id == null) {// 用户添加
            id = sysUser.getId();
        } else {// 用户编辑
            boolean flag = service.removeByUserId(id);// 删除用户角色信息
            if(!flag) return false;
        }
        if (Strings.isEmpty(roleIds)) return true;

        List<SysUserRole> list = new ArrayList<>();
        String[] split = roleIds.split(",");
        for (String roleId : split) {
            SysUserRole role = new SysUserRole();
            role.setRoleId(Short.parseShort(roleId));
            role.setUserId(id);
            list.add(role);
        }
        return service.saveBatch(list);
    }

    @Override
    public LoginVo login(LoginReqVo reqVo) {
        if (reqVo == null) return JsonVos.raise("登录失败");
        //查找信息
        MyLambdaQueryWrapper<SysUser> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, reqVo.getUsername());
        SysUser sysUser = baseMapper.selectOne(wrapper);
        // 用户是否存在
        if (sysUser == null) return JsonVos.raise(CodeMsg.WRONG_USERNAME);
        // 密码是否正确
        if (!sysUser.getPassword().equals(reqVo.getPassword())) return JsonVos.raise(CodeMsg.WRONG_PASSWORD);
        // 账号是否锁定
        if (sysUser.getStatus() == Constants.SysUserStatus.LOCKED) return JsonVos.raise(CodeMsg.USER_LOCKED);
        // 更新用户登录时间
        sysUser.setLoginTime(new Date());
        baseMapper.updateById(sysUser);
        // 设置用户基本信息到cache
        SysUserDto userDto = new SysUserDto();
        userDto.setUser(sysUser);
        List<Short> roleIds = service.listByUserId(sysUser.getId());
        if (roleIds != null) {
            List<SysRole> sysRoles = roleService.listByRoles(roleIds);
            if (sysRoles != null ) {
                userDto.setRoles(sysRoles);
            }
            List<Short> resourceIds = roleResourceService.listByRoleId(roleIds);
            if (resourceIds != null) {
                List<SysResource> sysResources = resourceService.listByResourceIds(resourceIds);
                userDto.setResources(sysResources);
            }
        }


        //设置token
        String token = UUID.randomUUID().toString();
        Caches.putToken(token, userDto);
        //将token设置
        LoginVo loginVo = new LoginVo();
        loginVo.setId(sysUser.getId());
        loginVo.setUsername(sysUser.getUsername());
        loginVo.setNickname(sysUser.getNickname());
        loginVo.setToken(token);
        return loginVo;
    }
}

