package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.domain.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description:
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    /**
     * 转账
     *
     * @param outUser 转出账户id
     * @param inUser  转入账户id
     * @param money
     */
    @Override
    public void transfer(Integer outUser, Integer inUser, Double money) {
        accountDao.out(outUser, money);
//        int i = 1 / 0;
        accountDao.in(inUser, money);
    }
}
