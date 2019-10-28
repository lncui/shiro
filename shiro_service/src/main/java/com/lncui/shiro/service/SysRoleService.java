package com.lncui.shiro.service;

import com.lncui.shiro.model.SysRole;

import java.util.List;

public interface SysRoleService extends BaseService<SysRole> {
    /**
     * 获取用户的角色
     *
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Integer userId);
}
