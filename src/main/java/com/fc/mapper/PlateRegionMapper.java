package com.fc.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fc.pojo.po.PlateRegion;
import com.fc.pojo.vo.list.ProvinceVo;

import java.util.List;

/**
 * (PlateRegion)表数据库访问层
 *
 * @author Florence
 * @since 2023/05/01
 */
public interface PlateRegionMapper extends BaseMapper<PlateRegion> {
    /*@Select("SELECT\n" +
            "            p.id, p.name, p.plate, city.id city_id,city.name city_name, city.plate city_plate\n" +
            "        FROM plate_region p LEFT JOIN\n" +
            "             plate_region city ON city.province_id = p.id\n" +
            "        WHERE p.province_id = 0\n" +
            "        ORDER BY p.pinyin asc, city.plate asc;")*/
    List<ProvinceVo> selectRegions();
}


