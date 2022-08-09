package org.example.utils;

import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description: 事务管理器，包含：
 * 开启事务
 * 提交事务
 * 回滚事务
 * 释放资源
 */
@Component("transactionManager")
@Aspect
public class TransactionManager {

    @Autowired
    private ConnectionUtils connectionUtils;

    @Pointcut("execution(* org.example.service.impl.AccountServiceImpl.transfer(..))")
    public void myPointCut() {
    }

    /**
     * 开启事务
     */
    @Before("TransactionManager.myPointCut()")
    public void beginTransaction() {
        Connection threadConnection = connectionUtils.getThreadConnection();

        try {
            threadConnection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    @AfterReturning("TransactionManager.myPointCut()")
    public void commit() {
        Connection threadConnection = connectionUtils.getThreadConnection();

        try {
            threadConnection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    @AfterThrowing("TransactionManager.myPointCut()")
    public void rollback() {
        Connection threadConnection = connectionUtils.getThreadConnection();

        try {
            threadConnection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    @After("TransactionManager.myPointCut()")
    public void release() {
        Connection threadConnection = connectionUtils.getThreadConnection();

        try {
            // 将手动提交事务改为自动提交事物
            threadConnection.setAutoCommit(true);
            connectionUtils.getThreadConnection().close();
            connectionUtils.removeThreadConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
