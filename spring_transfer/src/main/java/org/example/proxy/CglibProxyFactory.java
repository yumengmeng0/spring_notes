package org.example.proxy;

import org.example.service.AccountService;
import org.example.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description: 采用cglib动态代理来对目标类（AccountServiceImpl）进行方法（transfer）增强（添加事务控制）
 */
@Component
public class CglibProxyFactory {

    @Autowired
    AccountService accountService;

    @Autowired
    private TransactionManager transactionManager;

    public AccountService createAccountServiceCglibProxy() {


        /**
         * 参数1：目标对象的字节码
         * 参数2：动作类，代理对象调用目标对象原方法时，会执行intercept方法
         */
        AccountService accountServiceProxy = (AccountService) Enhancer.create(accountService.getClass(), new MethodInterceptor() {
            /**
             *
             * @param o 生成的代理对象
             * @param method 调用目标方法的引用
             * @param objects 方法入参
             * @param methodProxy 代理方法
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                try {
                    transactionManager.beginTransaction();
                    method.invoke(accountService, objects);
                    transactionManager.commit();
                } catch (Exception e) {
                    transactionManager.rollback();
                    e.printStackTrace();
                } finally {
                    transactionManager.release();
                }

                return null;
            }
        });

        return accountServiceProxy;
    }

}
