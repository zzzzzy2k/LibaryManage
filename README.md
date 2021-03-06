# 项目介绍

## 基础环境

MySQl 5.7.33，Tomcat 9.0.44， Maven 3.6.1，JDK 14

## 项目简介

在运行程序后，访问链接为：http://localhost:8081

这是一个简单的ssm图书管理系统。主要功能是对书本进行增加、删除、修改以及查询。

主要用到的技术除了有SpringBoot、SpringMVC和Mybatis之外，还使用了MySQl对数据进行存储。

用户的权限管理用到了Shiro框架，除了对用户进行权限控制外，对用户的密码也进行了加密。前端主要是用到了thymeleaf框架进行美化。

用户主要分为两类，一类是只有查询功能，另一类除了有查询功能外，还有新增书籍、修改书籍信息和删除书籍功能。

由于没有开发注册用户功能，所以这里直接提供了两个不同权限的用户用于测试查看。

**第一类**

- 用户名：**guest**
- 密码：**123456**

**第二类**

- 用户名：**admin**
- 密码：**123456**

## 杂谈

第一个自己写的小项目，仅仅实现了最基本的简单功能而已。这个项目仍然有许多不足的地方，比如用户的注册功能、管理员对用户的授权功能、读者对图书的借书还书功能等。

显然这个项目并不是那么完整，但我认为，对于熟悉SSM框架流程的构建和对各模块进行分析的学习是很有用的。

希望大家在学习后可以点个star:star:呀！

:heart::heart::heart: