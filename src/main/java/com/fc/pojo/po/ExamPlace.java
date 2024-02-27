package com.fc.pojo.po;

import com.fc.common.foreign.anno.ForeignField;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author Florence
 * @since 2023/05/02
 */
public class ExamPlace {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 所属省份
     */
    @ForeignField(mainTable = PlateRegion.class)
    private Integer provinceId;
    /**
     * 所属城市
     */
    @ForeignField(mainTable = PlateRegion.class)
    private Integer cityId;
    /**
     * 是否禁用，0：启用1：禁用
     */
    private Byte disable;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     *  经度
     */
    private BigDecimal longitude;
    /**
     * 具体地址
     */
    private String address;


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

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Byte getDisable() {
        return disable;
    }

    public void setDisable(Byte disable) {
        this.disable = disable;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}


