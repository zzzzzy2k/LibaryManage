package com.zzzzzy.config;

import com.zzzzzy.entity.User;
import com.zzzzzy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserRealm
 * @Description TODO
 * @Author zzzzy
 * @Date 2021/5/7 22:57
 * @Version 1.0
 **/

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    // 授权，权限相关
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo方法");

        // 获取登录用户名
        User user = (User) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<Map<String ,Object>> powerList = userService.getUserPower(user.getUsername());
        // System.out.println(powerList.toString());
        for (Map<String, Object> powerMap : powerList) {
            // 添加角色
            info.addRole(String.valueOf(powerMap.get("roleName")));
            // 添加权限
            info.addStringPermission(String.valueOf(powerMap.get("permissionsName")));
        }
        return info;
    }

    // 认证，身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");

        //  用户名、密码，从数据中取
        // 令牌
        UsernamePasswordToken userToken = (UsernamePasswordToken)token;
        System.out.println("密码为："+((UsernamePasswordToken) token).getPassword());
        String a = userToken.toString();
        String userName = userToken.getUsername();
        String userPwd = userToken.getPassword().toString();
        System.out.println("userName: "+userName);
        System.out.println("userPwd: "+userPwd);

        User user = userService.queryByName(userName);
        System.out.println(user.toString());
        if(user == null){
            return null;
        }
        if(!user.getUsername().equals(userName)){
            // 抛出异常
            return null;
        }

        // 盐值，这里使用账号作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());

        // 密码认证
        return new SimpleAuthenticationInfo(user,user.getPassword(), credentialsSalt,getName());
    }
}
