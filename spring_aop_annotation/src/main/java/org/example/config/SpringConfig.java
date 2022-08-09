package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description:
 */
@Configuration
@ComponentScan("org.example")
@EnableAspectJAutoProxy // 开启AOP自动代理 <aop:aspectj-autoproxy/>
public class SpringConfig {


}
