package com.fc.pojo.po;

import java.util.Date;

/**
 * @author Florence
 * @since 2023/05/18
 */
public class SysUser {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 登录用的用户名
     */
    private String username;
    /**
     * 登录用的密码
     */
    private String password;
    /**
     * 创建的时间
     */
    private Date createTime;
    /**
     * 最后一次登录的时间
     */
    private Date loginTime;
    /**
     * 账号的状态，0是正常，1是锁定
     */
    private Short status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

}


