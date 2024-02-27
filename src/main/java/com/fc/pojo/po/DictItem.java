package com.fc.pojo.po;

import com.fc.common.foreign.anno.ForeignField;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author florence
 * @since 2023/04/27
 */
public class DictItem {
    
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
     * 序号，值越大搜索越靠前（范围：0-255）
     */
    private Short sn;
    /**
     * 是否启用；0表示启用，1表示禁用
     */
    private Byte disable;
    @ForeignField(DictType.class)
    private Integer typeId;


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

    public Short getSn() {
        return sn;
    }

    public void setSn(Short sn) {
        this.sn = sn;
    }

    public Byte getDisable() {
        return disable;
    }

    public void setDisable(Byte disable) {
        this.disable = disable;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}


