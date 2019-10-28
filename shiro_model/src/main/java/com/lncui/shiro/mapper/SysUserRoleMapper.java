package com.lncui.shiro.mapper;

import com.lncui.shiro.model.SysUserRole;

import java.util.List;

/*
* 系统用户角色Mapper
* */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<Integer> findUserIdByRoleId(Integer roleId);
}
