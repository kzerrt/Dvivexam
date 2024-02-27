package com.fc.pojo.po;


/**
 * @author Florence
 * @since 2023/05/18
 */
public class SysUserRole {
    private static final long serialVersionUID = 122720557000951909L;
    /**
     * 角色id
     */
    private Short roleId;
    /**
     * 用户id
     */
    private Integer userId;


    public Short getRoleId() {
        return roleId;
    }

    public void setRoleId(Short roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}


