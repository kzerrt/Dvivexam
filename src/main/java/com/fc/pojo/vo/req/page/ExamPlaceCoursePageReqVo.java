package com.fc.pojo.vo.req.page;

/**
 * @author Florence
 * @since 2023/04/28
 */


public class ExamPlaceCoursePageReqVo extends KeywordPageReqVo {
    private Integer provinceId;
    private Integer cityId;
    private Integer placeId;
    private Short type;

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
