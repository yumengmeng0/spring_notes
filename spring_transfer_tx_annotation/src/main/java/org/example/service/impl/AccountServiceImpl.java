package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description:
 */
@Service
@Transactional
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
//    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ,
//            timeout = -1, readOnly = false)
    public void transfer(Integer outUser, Integer inUser, Double money) {
        accountDao.out(outUser, money);
//        int i = 1 / 0;
        accountDao.in(inUser, money);
    }
}
