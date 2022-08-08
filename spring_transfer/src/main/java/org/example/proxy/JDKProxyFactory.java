package org.example.proxy;

import org.example.service.AccountService;
import org.example.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description: JDK动态代理工厂类
 */
@Component
public class JDKProxyFactory {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionManager transactionManager;

    /**
     * 采用JDK动态代理技术生成目标类的代理对象
     * ClassLoder loader：类加载器，借助被代理对象获取到类加载器
     * InvocationHandler h：当代理对象调用接口中的任意方法，都会执行InvocationHandler中的invoke方法。
     *
     * @return
     */
    public AccountService createAccountServiceJDKProxy() {


        AccountService accountServiceProxy = (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {

            /**
             *
             * @param proxy 当前代理对象的引用
             * @param method 被调用目标方法的引用
             * @param args 被调用的目标方法的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                try {
                    // 手动开启事务：调用事务管理器类中的开启事务方法
                    transactionManager.beginTransaction();

                    method.invoke(accountService, args); // 被代理对象的原方法执行

                    // 手动提交事务
                    transactionManager.commit();
                    System.out.println("commit");
                } catch (Exception e) {
                    e.printStackTrace();
                    // 手动回滚事务
                    transactionManager.rollback();
                    System.out.println("rollback");
                } finally {
                    // 释放资源
                    transactionManager.release();
                    System.out.println("release");
                }


                return null;
            }
        });

        return accountServiceProxy;
    }

}
