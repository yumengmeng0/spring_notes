package org.example.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description: 通知类
 */
@Component
@Aspect // 升级为切面类，配置切入点和通知的关系
public class MyAdvice {

    @Pointcut("execution(* org.example.service.impl.AccountServiceImpl.*(..))")
    public void myPoint() {
    }

//    @Before("MyAdvice.myPoint()")
//    public void before() {
//        System.out.println("前置通知，MyAdvice.before");
//    }
//
//    @AfterReturning("MyAdvice.myPoint()")
//    public void afterReturning() {
//        System.out.println("后置通知，MyAdvice.afterReturning");
//    }
//
//    @AfterThrowing("MyAdvice.myPoint()")
//    public void afterThrowing() {
//        System.out.println("异常通知，MyAdvice.afterThrowing");
//    }
//
//    @After("MyAdvice.myPoint()")
//    public void after() {
//        System.out.println("最终通知，MyAdvice.after");
//    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint 正在执行的连接点（切点对象）
     * @return
     */
    @Around("MyAdvice.myPoint()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("MyAdvice.around");
        Object proceed = null;
        try {
//            int i = 1 / 0;
            System.out.println("前置通知，MyAdvice.before");
            proceed = proceedingJoinPoint.proceed(); // 切点方法执行
            System.out.println("后置通知，MyAdvice.afterReturning");
        } catch (Throwable throwable) {
            System.out.println("异常通知，MyAdvice.afterThrowing");
            throwable.printStackTrace();
        } finally {
            System.out.println("最终通知，MyAdvice.after");
        }
        return proceed;
    }
}
