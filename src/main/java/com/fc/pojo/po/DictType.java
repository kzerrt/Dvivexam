package com.fc.pojo.po;

import com.fc.common.foreign.anno.ForeignTable;

import javax.validation.constraints.NotBlank;

/**
 * @author florence
 * @since 2023/04/27
 */
public class DictType {
    /**
     * id标识符
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 名称对应值
     */
    private String value;
    /**
     * 简介
     */
    private String intro;


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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

}


