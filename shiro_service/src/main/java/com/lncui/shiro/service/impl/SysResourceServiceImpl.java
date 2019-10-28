package com.lncui.shiro.service.impl;

import com.lncui.shiro.mapper.BaseMapper;
import com.lncui.shiro.mapper.SysResourceMapper;
import com.lncui.shiro.model.SysResources;
import com.lncui.shiro.model.SysRole;
import com.lncui.shiro.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResources> implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    @Override
    public BaseMapper<SysResources> getBaseMapper() {
        return null;
    }


    /**
     * 获取有资源
     *
     * @param
     * @return
     */
    @Override
    public List<SysResources> listAll() {
        return sysResourceMapper.listAll();
    }

    /**
     * 获取用户关联的所有资源
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysResources> listByUserId(Integer userId) {
        return sysResourceMapper.listByUserId(userId);
    }
}
