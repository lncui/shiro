package com.lncui.shiro.vo;

import java.util.Date;
import java.util.List;

/*
*  系统资源Vo
* */

public class SysResourcesVO {

    private Integer id;
    private String name;
    private String type;
    private String url;
    private String permission;
    private Integer parentId;
    private Integer sort;
    private Boolean external;
    private Boolean available;
    private String icon;
    private String checked;
    private SysResourcesVO parent;
    private List<SysResourcesVO> nodes;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public SysResourcesVO getParent() {
        return parent;
    }

    public void setParent(SysResourcesVO parent) {
        this.parent = parent;
    }

    public List<SysResourcesVO> getNodes() {
        return nodes;
    }

    public void setNodes(List<SysResourcesVO> nodes) {
        this.nodes = nodes;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
