package com.fc.pojo.po;

import com.fc.common.foreign.anno.ForeignField;


/**
 * @author Florence
 * @since 2023/05/01
 */
public class PlateRegion {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 车牌
     */
    private String plate;
    /**
     * 拼音
     */
    private String pinyin;
    /**
     * 所属省份
     */
    @ForeignField(mainTable = PlateRegion.class)
    private Integer provinceId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

}


