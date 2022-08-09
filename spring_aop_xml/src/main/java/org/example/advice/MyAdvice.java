package org.example.advice;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description: 通知类
 */
public class MyAdvice {


    public void before() {
        System.out.println("前置通知，MyAdvice.before");
    }

    public void afterReturning() {
        System.out.println("后置通知，MyAdvice.afterReturning");
    }

    public void afterThrowing() {
        System.out.println("异常通知，MyAdvice.afterThrowing");
    }

    public void after() {
        System.out.println("最终通知，MyAdvice.after");
    }

    /**
     * 环绕通知
     * @param proceedingJoinPoint 正在执行的连接点（切点对象）
     * @return
     */
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("MyAdvice.around");
        Object proceed = null;
        try {
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
