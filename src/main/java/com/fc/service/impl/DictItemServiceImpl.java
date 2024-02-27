package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.enchance.MyPage;
import com.fc.common.mapStruct.MapStructs;
import com.fc.pojo.po.DictItem;
import com.fc.mapper.DictItemMapper;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.DictItemVo;
import com.fc.pojo.vo.req.page.DictItemPageReqVo;
import com.fc.service.DictItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (DictItem)表服务实现类
 *
 * @author florence
 * @since 2023/04/27
 */
@Service
@Transactional
public class DictItemServiceImpl
        extends ServiceImpl<DictItemMapper, DictItem>
        implements DictItemService {

    @Override
    @Transactional(readOnly = true)
    public PageVo<DictItemVo> list(DictItemPageReqVo query) {
        //创造查询条件
        MyLambdaQueryWrapper<DictItem> wrapper = new MyLambdaQueryWrapper<>();
        wrapper.like(query.getKeyword(), DictItem::getName, DictItem::getValue);
        Integer typeId = query.getTypeId();
        if(typeId != null && typeId > 0) {
            wrapper.eq(DictItem::getTypeId, typeId);
        }
        //根据id倒序排序
        wrapper.orderByDesc(DictItem::getTypeId);
        //分页
        MyPage<DictItem> page = new MyPage<>(query);
        //根据条件查询
        return baseMapper.selectPage(page, wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }
}

