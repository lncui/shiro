package com.lncui.shiro.mapper;



import com.lncui.shiro.model.SysRole;

import java.util.List;

/*
* 系统角色Mapper
* */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> queryRoleListWithSelected(Integer userId);

    /*
    * 获取用户的角色
    * */
    List<SysRole> listRolesByUserId(Integer userId);
}
