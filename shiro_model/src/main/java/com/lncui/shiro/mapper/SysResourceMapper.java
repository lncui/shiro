package com.lncui.shiro.mapper;

        import com.lncui.shiro.model.SysResources;

        import java.util.List;
        import java.util.Map;

/*
* 系统资源Mapper
* */
public interface SysResourceMapper extends BaseMapper<SysResources> {


    List<SysResources> listUserResources(Map<String, Object> map);

    List<SysResources> queryResourcesListWithSelected(Integer rid);

    List<SysResources> listUrlAndPermission();

    List<SysResources> listAllAvailableMenu();

    //获取用户关联的所有资源
    List<SysResources> listByUserId(Integer userId);

    //获取有资源
    List<SysResources> listAll();
}
