# Spring Notes

# 一、Spring概述

    * Spring概念介绍
    * IOC
    * DBUtils
    * Spring注解开发
    * Spring整合Junit

## 1.1 什么是Spring
    Spring是分成的Java SE/EE应用的full-stack（全栈式）轻量级开源框架。
    
    full-stack（全栈式）：对各种主流技术和框架进行整合，同时对三层框架都提供解决方案
    轻量级：轻量级和重量级划分依据是使用了多少服务，启动时需要加载的资源多少以及耦合度等
    
    表现层：SpringMVC
    持久层：Spring JDBC Template
    业务层；事务管理
    
    Spring两大核心：
        IOC（Inverse Of Control）：控制反转，把对象的创建权交给Spring
        AOP（Aspect Oriented Programming）：面向切面编程，在不修改源码的情况下，对方法进行增强（动态代理）

