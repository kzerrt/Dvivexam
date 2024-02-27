package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.SysUser;
import com.fc.pojo.result.CodeMsg;
import com.fc.pojo.vo.LoginVo;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.SysUserVo;
import com.fc.pojo.vo.req.LoginReqVo;
import com.fc.pojo.vo.req.page.SysUserPageReqVo;
import com.fc.pojo.vo.req.save.SysUserReqVo;

/**
 * 用户（可以登录后台系统的）(SysUser)表服务接口
 *
 * @author Florence
 * @since 2023/05/18
 */
public interface SysUserService extends IService<SysUser> {

    PageVo<SysUserVo> list(SysUserPageReqVo reqVo);

    boolean saveOrUpdateUser(SysUserReqVo reqVo);

    LoginVo login(LoginReqVo reqVo);
}

