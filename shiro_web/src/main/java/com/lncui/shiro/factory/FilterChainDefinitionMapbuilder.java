package com.lncui.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapbuilder {
    public LinkedHashMap<String,String> bulidFilterChainDefinitionMap(){
        LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();

        map.put("/passport/login", "anon");// 登录页面不拦截
        map.put("/passport/signin", "anon");//登录
        map.put("/passport/unauthorized", "anon");//权限异常
//        map.put("/passport/logout","logout");  //Shiro提供了退出登录的配置`logout`，会生成路径为`/logout`的请求地址，访问这个地址即会退出当前账户并清空缓存

        map.put("/**", "authc");//authc表示访问该地址用户必须身份验证通过，即Subject.isAuthenticated() == true
        return map;
    }
}
