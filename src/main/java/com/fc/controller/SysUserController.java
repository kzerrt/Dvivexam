package com.fc.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.common.cache.Caches;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.shiro.TokenFilter;
import com.fc.common.util.Constants;
import com.fc.common.util.JsonVos;
import com.fc.common.util.Streams;
import com.fc.pojo.po.SysUser;
import com.fc.pojo.result.CodeMsg;
import com.fc.pojo.vo.DataJsonVo;
import com.fc.pojo.vo.JsonVo;
import com.fc.pojo.vo.LoginVo;
import com.fc.pojo.vo.PageJsonVo;
import com.fc.pojo.vo.list.SysUserVo;
import com.fc.pojo.vo.req.LoginReqVo;
import com.fc.pojo.vo.req.page.SysUserPageReqVo;
import com.fc.pojo.vo.req.save.SysUserReqVo;
import com.fc.service.SysUserService;
import com.wf.captcha.utils.CaptchaUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;


/**
 * 用户（可以登录后台系统的）(SysUser)表控制层
 *
 * @author Florence
 * @since 2023/05/18
 */
@RestController
@RequestMapping("/sysUsers")
public class SysUserController extends BaseController<SysUser, SysUserReqVo> {
    @Autowired
    private SysUserService service;
    @GetMapping
    @ApiOperation("分页查询")
    @RequiresPermissions(value = {Constants.Permisson.SYS_USER_LIST})
    public PageJsonVo<SysUserVo> list(SysUserPageReqVo reqVo) {
        return JsonVos.ok(service.list(reqVo));
    }
    @GetMapping("/list")
    @ApiOperation("查询所有")
    @RequiresPermissions(value = {Constants.Permisson.SYS_USER_LIST})
    public DataJsonVo<List<SysUserVo>> list() {
        return JsonVos.ok(Streams.stream(service.list(), MapStructs.INSTANCE::po2vo));
    }
    @PostMapping("/saveUser")
    @ApiOperation("保存用户及角色信息")
    @RequiresPermissions(value = {
            Constants.Permisson.SYS_USER_UPDATE,
            Constants.Permisson.SYS_USER_ADD
    }, logical = Logical.AND)
    public JsonVo saveUser(SysUserReqVo reqVo) {
        boolean flag = service.saveOrUpdateUser(reqVo);
        if (flag) {
            return JsonVos.ok();
        } else {
            return JsonVos.raise("保存失败！");
        }
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public DataJsonVo<LoginVo> login(LoginReqVo reqVo, HttpServletRequest request) {
        if (CaptchaUtil.ver(reqVo.getCaptcha(), request)) {
            return JsonVos.ok(service.login(reqVo));
        }
        return JsonVos.raise(CodeMsg.WRONG_CAPTCHA);
    }
    @GetMapping("/logout")
    public JsonVo logout(@RequestHeader(TokenFilter.HEADER_TOKEN) String token) {
        Caches.removeToken(token);
        return JsonVos.ok();
    }
    @GetMapping("/captcha")
    @ApiOperation("验证码")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CaptchaUtil.out(request, response);
    }

    @Override
    protected IService<SysUser> getService() {
        return service;
    }

    @Override
    protected Function<SysUserReqVo, SysUser> getFunction() {
        return MapStructs.INSTANCE::reqVo2po;
    }
}


