package com.fc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.common.enchance.MyPage;
import com.fc.common.enchance.MyQueryWrapper;
import com.fc.common.mapStruct.MapStructs;
import com.fc.pojo.po.ExamPlaceCourse;
import com.fc.mapper.ExamPlaceCourseMapper;
import com.fc.pojo.vo.PageVo;
import com.fc.pojo.vo.list.ExamPlaceCourseVo;
import com.fc.pojo.vo.req.page.ExamPlaceCoursePageReqVo;
import com.fc.service.ExamPlaceCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 考场课程(ExamPlaceCourse)表服务实现类
 *
 * @author Florence
 * @since 2023/05/05
 */
@Service
@Transactional
public class ExamPlaceCourseServiceImpl extends ServiceImpl<ExamPlaceCourseMapper,ExamPlaceCourse>
        implements ExamPlaceCourseService {

    @Override
    public PageVo<ExamPlaceCourseVo> list(ExamPlaceCoursePageReqVo query) {
        //创建查询条件
        MyQueryWrapper<ExamPlaceCourseVo> wrapper = new MyQueryWrapper<>();
        Short type = query.getType();
        if (type != null && type >= 0) {
            wrapper.eq("c.type", type);
        }
        // 设置查询参数
        wrapper.like(query.getKeyword(), "c.name", "c.intro");
        Integer id = query.getPlaceId();
        if (id != null && id > 0) {
            wrapper.eq("c.id", id);
        } else if ((id = query.getCityId()) != null && id > 0) {
            wrapper.eq("p.city_id", id);
        } else if ((id = query.getProvinceId()) != null && id > 0) {
            wrapper.eq("p.province_id", id);
        }
        // 查询结果
        return baseMapper.selectPageVos(new MyPage<>(query), wrapper)
                .buildVo();
    }
}

