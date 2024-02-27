package com.fc.common.mapStruct;

import com.fc.pojo.po.*;
import com.fc.pojo.vo.list.*;
import com.fc.pojo.vo.req.save.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 类型转换，将vo转换为po或是其他
 * @author Florence
 * @since 2023/05/06
 */

@Mapper(uses = {
        MapStructFormatter.class
})
public interface MapStructs {
    MapStructs INSTANCE = Mappers.getMapper(MapStructs.class);
    DictItemVo po2vo(DictItem po);
    DictTypeVo po2vo(DictType po);
    ExamPlaceVo po2vo(ExamPlace po);
    ExamPlaceCourseVo po2vo(ExamPlaceCourse po);
    PlateRegionVo po2vo(PlateRegion po);
    @Mapping(source = "loginTime",
            target = "loginTime",
            qualifiedBy = MapStructFormatter.Date2Millis.class)
    SysUserVo po2vo(SysUser po);
    SysRoleVo po2vo(SysRole po);
    SysResourceVo po2vo(SysResource po);

    DictItem reqVo2po(DictItemReqVo reqVo);
    DictType reqVo2po(DictTypeReqVo reqVo);
    ExamPlace reqVo2po(ExamPlaceReqVo reqVo);
    ExamPlaceCourse reqVo2po(ExamPlaceCourseReqVo reqVo);
    PlateRegion reqVo2po(PlateRegionReqVo reqVo);
    SysUser reqVo2po(SysUserReqVo po);
    SysRole reqVo2po(SysRoleReqVo po);
    SysResource reqVo2po(SysResourceReqVo po);
}
