package com.lncui.shiro.service.impl;

import com.lncui.shiro.mapper.BaseMapper;
import com.lncui.shiro.mapper.SysUserMapper;
import com.lncui.shiro.model.SysUser;
import com.lncui.shiro.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public BaseMapper<SysUser> getBaseMapper() {
        return sysUserMapper;
    }

    @Override
    public SysUser getByUserName(String username) {
        return sysUserMapper.getByUserName(username);
    }
}
