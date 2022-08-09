package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ymm
 * @date: 2022/8/7
 * @version: 1.0.0
 * @description:
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    /**
     * 转账
     *
     * @param outUser
     * @param inUser
     * @param money
     */
    @Override
    public void transfer(String outUser, String inUser, Double money) {
        accountDao.out(outUser, money);
//        int i = 1 / 0;
        accountDao.in(inUser, money);
    }

    @Override
    public void save() {
        System.out.println("save");
    }

    @Override
    public void update() {
        System.out.println("update");
    }

    @Override
    public void delete() {
        System.out.println("delete");
    }
}
