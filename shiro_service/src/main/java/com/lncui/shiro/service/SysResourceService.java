package com.lncui.shiro.service;

import com.lncui.shiro.model.SysResources;

import java.util.List;

public interface SysResourceService extends BaseService<SysResources> {

    /**
     * 获取有资源
     *
     * @param
     * @return
     */
    List<SysResources> listAll();

    /**
     * 获取用户关联的所有资源
     *
     * @param userId
     * @return
     */
    List<SysResources> listByUserId(Integer userId);
}
