package com.zzzzzy.controller;

import com.zzzzzy.entity.User;
import com.zzzzzy.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author zzzzy
 * @Date 2021/5/7 22:06
 * @Version 1.0
 **/

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession session, Integer rememberMe) {
        // 获取一个用户
        Subject subject = SecurityUtils.getSubject();
        // System.out.println("密码为："+password);
        // Shiro自带的md5加密
        // String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 开启记住我功能
        if(rememberMe != null && rememberMe == 1){
            token.setRememberMe(true);
        }

        try {
            subject.login(token);
            subject.hasRole("登录");
            return "redirect:/book/list";
        } catch (AuthenticationException e) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    @RequestMapping("/noRole")
    @ResponseBody
    public String unauthorized(){
        return "未授权，无法访问次页面";
    }


}
