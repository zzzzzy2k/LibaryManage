package com.zzzzzy.config;

import com.zzzzzy.entity.User;
import com.zzzzzy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName AddPrincipalToSessionFilter
 * @Description 解决Session丢失
 * @Author zzzzy
 * @Date 2021/5/9 10:47
 * @Version 1.0
 **/

public class AddPrincipalToSessionFilter extends OncePerRequestFilter {

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 查询当前用户的信息
        Subject subject = SecurityUtils.getSubject();
        // 判断用户是不是通过自动登录进来的
        if (subject.isRemembered()) {
            User user2 = (User) subject.getPrincipal();
            String username = user2.getUsername();
            System.out.println(username + "......");
            if (username == null) {
                return;
            }
            // 根据用户名查询该用户的信息
            User user = userService.queryByName(username);
            if (user == null) return;
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                // 查询到的用户信息设置为session，时效为3600秒
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(3600);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
