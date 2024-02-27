package com.fc.pojo.dto;

import com.fc.pojo.po.SysResource;
import com.fc.pojo.po.SysRole;
import com.fc.pojo.po.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author Florence
 * @since 2023/06/14
 */

@Data
public class SysUserDto {
    private SysUser user;
    private List<SysRole> roles;
    private List<SysResource> resources;
}
