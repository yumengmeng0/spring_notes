package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.service.AccountService;
import org.example.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ymm
 * @date: 2022/8/7
 * @version: 1.0.0
 * @description:
 */
@Service("accountService1")
public class AccountServiceImplWithoutProxy implements AccountService {
    @Autowired
    private AccountDao accountDao;
    // 移到动态代理工厂类中
    @Autowired
    private TransactionManager transactionManager;

    /**
     * 转账
     *
     * @param outUser
     * @param inUser
     * @param money
     */
    @Override
    public void transfer(String outUser, String inUser, Double money) {
        // 手动开启事务：调用事务管理器类中的开启事务方法
        transactionManager.beginTransaction();

        try {
        accountDao.out(outUser, money);
        accountDao.in(inUser, money);

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

    }

    @Override
    public void save() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }
}
