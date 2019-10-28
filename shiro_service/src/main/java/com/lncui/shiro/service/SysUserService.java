package com.lncui.shiro.service;

import com.lncui.shiro.model.SysUser;

public interface SysUserService extends BaseService<SysUser> {

    SysUser getByUserName(String username);
}
