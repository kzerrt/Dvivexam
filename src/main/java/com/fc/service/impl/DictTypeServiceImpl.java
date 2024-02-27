package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.enchance.MyLambdaQueryWrapper;
import com.fc.common.enchance.MyPage;
import com.fc.common.mapStruct.MapStructs;
import com.fc.pojo.po.DictType;
import com.fc.mapper.DictTypeMapper;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.DictTypeVo;
import com.fc.pojo.vo.req.page.DictTypePageReqVo;
import com.fc.service.DictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (DictType)表服务实现类
 *
 * @author florence
 * @since 2023/04/27
 */
@Service
@Transactional
public class DictTypeServiceImpl
        extends ServiceImpl<DictTypeMapper,DictType> implements DictTypeService {


    @Override
    public PageVo<DictTypeVo> list(DictTypePageReqVo query) {
        //创造查询条件
        MyLambdaQueryWrapper<DictType> wrapper = new MyLambdaQueryWrapper<>();
        String keyword = query.getKeyword();
        wrapper.like(keyword, DictType::getName, DictType::getValue, DictType::getIntro);
            /*wrapper.like(DictType::getName, keyword).or()
                    .like(DictType::getValue, keyword).or()
                    .like(DictType::getIntro, keyword);*/
        //根据id倒序排序
        wrapper.orderByDesc(DictType::getId);
        //分页
        MyPage<DictType> page = new MyPage<>(query);
        //根据条件查询
        return baseMapper
                .selectPage(page, wrapper)
                .buildVo(MapStructs.INSTANCE::po2vo);
    }
}

