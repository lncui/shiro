package com.lncui.shiro.realms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lncui.shiro.enums.UserStatusEnum;
import com.lncui.shiro.enums.UserTypeEnum;
import com.lncui.shiro.model.SysResources;
import com.lncui.shiro.model.SysRole;
import com.lncui.shiro.model.SysUser;
import com.lncui.shiro.service.SysResourceService;
import com.lncui.shiro.service.SysRoleService;
import com.lncui.shiro.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysResourceService sysResourceService;

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("[FirstShiroRealm] doGetAuthenticationInfo");

        // 1. 把AuthenticationToken 转换为UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        // 2.从UsernamePasswordToken 中来获取用户输入的username
        String username = upToken.getUsername();

        // 3.调用数据库的方法，从数据库中查询username对应的用户记录
        SysUser user = sysUserService.getByUserName(username);

        // 4. 若用户不存在，则可以抛出UnknownAccountException异常
        if(user == null){
            throw new UnknownAccountException("账号不存在！");
        }

        // 5.根据用户信息的情况，决定是否需要抛出其他的AuthentacationException异常
        if (user.getStatus() != null && UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedAccountException("帐号已被锁定，禁止登录！");
        }

        // 6. 根据用户的情况，来构建AuthenticationInfo 对象并返回，通常使用的实现类为：SimpleAuthenticationInfo
        //以下信息是从数据库中获取的.
        //1). principal:认证的实体信息，可以是username，也可以是数据表对应的用户的实体类对象。
        Object principal = user;
        //2). credentials:密码。
        Object credentials = user.getPassword(); //"fc1709d0a95a6be30bc5926fdb7f22f4";
        //3). realmName:当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();

        //4). 盐值.
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        SimpleAuthenticationInfo info = null; //new SimpleAuthenticationInfo(principal,credentials,realmName);
        info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
    }

    public static void main(String [] args) {

        String hashAlgorithmName="MD5";
        Object credentials="123456";
        Object salt=ByteSource.Util.bytes("user");
        int hashIterations=1024;

        Object res = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(res);
    }

    //授权会被shiro回调的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 1.getPrincipal 中来获取登录用户
        SysUser userToken = (SysUser) SecurityUtils.getSubject().getPrincipal();

        //2.利用登录的用户的信息来用当前用户的角色和权限（可能需要查询数据库）
        // 赋予角色
        List<SysRole> roleList = sysRoleService.listRolesByUserId(userToken.getId());

        for (SysRole role : roleList) {
            info.addRole(role.getName());
        }

        // 赋予权限
        List<SysResources> resourcesList = null;

        SysUser user = sysUserService.getByID(userToken.getId());
        if(user == null){
            return info;
        }

        // ROOT用户默认拥有所有权限
        if(UserTypeEnum.ROOT.toString().equals(user.getUserType())){
            resourcesList = sysResourceService.listAll();
        }else{
            resourcesList = sysResourceService.listByUserId(userToken.getId());
        }

        if (!CollectionUtils.isEmpty(resourcesList)) {
            Set<String> permissionSet = new HashSet<>();
            for (SysResources resources : resourcesList) {
                String permission = null;
                if (!StringUtils.isEmpty(permission = resources.getPermission())) {
                    permissionSet.addAll(Arrays.asList(permission.trim().split(",")));
                }
            }
            info.setStringPermissions(permissionSet);
        }

        //4.返回SimpleAuthorizationInfo
        return info;
    }

}
