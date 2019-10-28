package com.lncui.shiro.mapper;



import com.lncui.shiro.model.SysUser;

import java.util.List;

/*
* 系统用户Mapper
* */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> listByRoleId(Integer roleId);

    SysUser getByUserName(String username);
}
