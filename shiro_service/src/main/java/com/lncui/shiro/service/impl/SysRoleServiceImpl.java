package com.lncui.shiro.service.impl;

import com.lncui.shiro.mapper.BaseMapper;
import com.lncui.shiro.mapper.SysRoleMapper;
import com.lncui.shiro.mapper.SysUserMapper;
import com.lncui.shiro.model.SysRole;
import com.lncui.shiro.model.SysUser;
import com.lncui.shiro.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public BaseMapper<SysRole> getBaseMapper() {
        return sysRoleMapper;
    }

    @Override
    public List<SysRole> listRolesByUserId(Integer userId) {
        return sysRoleMapper.listRolesByUserId(userId);
    }
}
