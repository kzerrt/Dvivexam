package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.enchance.MyPage;
import com.fc.common.mapStruct.MapStructs;
import com.fc.common.util.Constants;
import com.fc.common.util.Streams;
import com.fc.pojo.po.SysResource;
import com.fc.mapper.SysResourceMapper;
import com.fc.pojo.po.SysRole;
import com.fc.pojo.po.SysRoleResource;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.SysResourceTreeVo;
import com.fc.pojo.vo.list.SysResourceVo;
import com.fc.pojo.vo.req.page.SysResourcePageReqVo;
import com.fc.service.SysResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 资源(SysResource)表服务实现类
 *
 * @author Florence
 * @since 2023/05/18
 */
@Service
@Transactional
public class SysResourceServiceImpl
        extends ServiceImpl<SysResourceMapper, SysResource>
        implements SysResourceService{

    @Override
    public PageVo<SysResourceVo> list(SysResourcePageReqVo reqVo) {
        //创造查询条件
        MyLambdaQueryWrapper<SysResource> wrapper = new MyLambdaQueryWrapper<>();
        String keyword = reqVo.getKeyword();
        wrapper.like(keyword, SysResource::getName);
        //分页
        MyPage<SysResource> page = new MyPage<>(reqVo);
        //根据条件查询
        return baseMapper.selectPage(page, wrapper).buildVo(MapStructs.INSTANCE::po2vo);
    }

    @Override
    public List<SysResourceVo> listParents() {
        //创造查询条件
        MyLambdaQueryWrapper<SysResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.ne(SysResource::getType, Constants.SysResourceType.BTN);
        return Streams.stream(baseMapper.selectList(wrapper),MapStructs.INSTANCE::po2vo);
    }

    @Override
    public List<SysResourceTreeVo> listTrees() {
        //创造查询条件
        MyLambdaQueryWrapper<SysResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.orderByAsc(SysResource::getType).orderByDesc(SysResource::getId);
        List<SysResource> pos = baseMapper.selectList(wrapper);
        //将po转换为树状结构
        List<SysResourceTreeVo> list = new LinkedList<>();
        Map<Short, SysResourceTreeVo> map = new HashMap<>();
        Short parentId;
        for (SysResource po : pos) {
            SysResourceTreeVo vo = po2treeVo(po);
            map.put(vo.getId(), vo);
            if (po.getType() == Constants.SysResourceType.DIR) {// 资源为目录类型
                list.add(vo);
            } else {
                parentId = po.getParentId();
                SysResourceTreeVo parent = map.get(parentId);
                List<SysResourceTreeVo> children = parent.getChildren();
                if (children == null) {
                    parent.setChildren(children = new ArrayList<>());
                }
                children.add(vo);
            }
        }
        return list;
    }

    @Override
    public List<SysResource> listByResourceIds(List<Short> resourceIds) {
        MyLambdaQueryWrapper<SysResource> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.in(SysResource::getId, resourceIds);
        return null;
    }

    private SysResourceTreeVo po2treeVo(SysResource sysResource) {
        SysResourceTreeVo tree = new SysResourceTreeVo();
        tree.setId(sysResource.getId());
        tree.setTitle(sysResource.getName());
        return tree;
    }
}

