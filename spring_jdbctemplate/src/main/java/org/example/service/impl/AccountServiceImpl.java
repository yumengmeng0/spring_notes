package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.domain.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 查询所有账户
     *
     * @return
     */
    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    /**
     * 根据id查找账户
     *
     * @param id
     * @return
     */
    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    /**
     * 添加账户
     *
     * @param account
     */
    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    /**
     * 更新账户
     *
     * @param account
     */
    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    /**
     * 根据id删除账户
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        accountDao.delete(id);
    }
}
