package com.zzzzzy.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShiroConfig
 * @Description TODO
 * @Author zzzzy
 * @Date 2021/5/7 23:01
 * @Version 1.0
 **/

@Configuration
public class ShiroConfig {

    /*
        shiro的三个核心概念：
        subject：代表正在执行操作的用户
        Security Manger：Shrio的核心，主要协调shiro内部的各种安全组件，只需要知道可以设置自定的realm即可
        Realm：用户数据和Shiro数据交换的桥梁。比如需要用户身份认证、权限认证...
     */

    // 3. 获取ShiroFillterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManger") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        // 设置登录的请求
        bean.setLoginUrl("/toLogin");
        // 未授权页面
        bean.setUnauthorizedUrl("/noRole");

        Map<String,String> filterMap = new LinkedHashMap<>();
        // 授权
        filterMap.put("/","anon");
        filterMap.put("/login","anon");
        filterMap.put("/index","anon");
        filterMap.put("/hello","anon");

        filterMap.put("/book/**","user");

        // 主要这行代码必须放在所有权限的设置的最后，不然会导致所有url都被拦截，剩余的都需要认证
        filterMap.put("/**","user");

        filterMap.put("/logout","logout");
        bean.setFilterChainDefinitionMap(filterMap);

        // 解决session丢失
        Map<String, Filter> fmap = new HashMap<>();
        fmap.put("addPrincipal", addPrincipalToSessionFilter());
        bean.setFilters(fmap);

        return bean;
        /*
        anon：无需认证就可以访问
        authc：必须认证了才能访问
        uesr：必须拥有记住我功能才能用
        perms：拥有对某个资源的权限才能访问
        role：拥有某个角色权限
         */
    }

    // 2. 获取DefaultWebSecuritymanger
    @Bean(name = "securityManger")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联userRealm
        securityManager.setRealm(userRealm);
        // 设置记住我
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }


    // 1. 创建realm对象，需要自定义类
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userRealm.setCachingEnabled(false);
        return userRealm;
    }

    // 整合ShiroDialect：用来整合Shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //setcookie的httponly属性如果设为true的话，会增加对xss防护的安全系数。它有以下特点：
        //setcookie()的第七个参数
        //设为true后，只能通过http访问，javascript无法访问
        //防止xss读取cookie
        simpleCookie.setHttpOnly(true);
        simpleCookie.setPath("/");
        simpleCookie.setMaxAge(3600);
        return simpleCookie;
    }

    // cookie管理对象
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe.cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    // shiro自定义过滤器（解决session丢失）
    @Bean
    public OncePerRequestFilter addPrincipalToSessionFilter(){
        return new AddPrincipalToSessionFilter();
    }

    // md5加密配置
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法：这里使用md5算法；
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于md5(md5())
        hashedCredentialsMatcher.setHashIterations(2);
        // storedCredentialsHexEncode默认是true，此时用的密码加密用的是Hex编码；false时用的是Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
}
