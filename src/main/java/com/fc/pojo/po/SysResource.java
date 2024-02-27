package com.fc.pojo.po;


/**
 * @author Florence
 * @since 2023/05/18
 */
public class SysResource {
    /**
     * 主键
     */
    private Short id;
    /**
     * 名称
     */
    private String name;
    /**
     * 链接地址
     */
    private String uri;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 资源类型（0是目录，1是菜单，2是目录）
     */
    private Short type;
    /**
     * 图标
     */
    private String icon;
    /**
     * 序号
     */
    private Short sn;
    /**
     * 父资源id
     */
    private Short parentId;


    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Short getSn() {
        return sn;
    }

    public void setSn(Short sn) {
        this.sn = sn;
    }

    public Short getParentId() {
        return parentId;
    }

    public void setParentId(Short parentId) {
        this.parentId = parentId;
    }

}


