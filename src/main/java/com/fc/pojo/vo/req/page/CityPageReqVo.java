package com.fc.pojo.vo.req.page;

/**
 * @author Florence
 * @since 2023/04/28
 */


public class CityPageReqVo extends KeywordPageReqVo {
    private Integer provinceId;

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}
