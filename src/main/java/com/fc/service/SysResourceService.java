package com.fc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.pojo.po.SysResource;
import com.fc.pojo.result.CodeMsg;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.SysResourceTreeVo;
import com.fc.pojo.vo.list.SysResourceVo;
import com.fc.pojo.vo.req.page.SysResourcePageReqVo;

import java.util.Collection;
import java.util.List;

/**
 * 资源(SysResource)表服务接口
 *
 * @author Florence
 * @since 2023/05/18
 */
public interface SysResourceService extends IService<SysResource> {

    PageVo<SysResourceVo> list(SysResourcePageReqVo reqVo);

    List<SysResourceVo> listParents();

    List<SysResourceTreeVo> listTrees();

    List<SysResource> listByResourceIds(List<Short> roleIds);
}

