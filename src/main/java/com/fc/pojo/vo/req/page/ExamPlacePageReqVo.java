package com.fc.pojo.vo.req.page;

/**
 * @author Florence
 * @since 2023/04/28
 */


public class ExamPlacePageReqVo extends KeywordPageReqVo {
    private Integer provinceId;
    private Integer cityId;

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
}
