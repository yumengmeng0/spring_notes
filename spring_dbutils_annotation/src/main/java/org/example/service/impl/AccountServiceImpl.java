package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.domain.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/7
 * @version: 1.0.0
 * @description:
 */
@Service("accountService") // 相当于配置Bean标签 id属性
@Scope("singleton")
public class AccountServiceImpl implements AccountService {

    //    @Autowired // 根据类型注入
//    @Qualifier("accountDao")
    @Resource(name = "accountDao")
    private AccountDao accountDao;

    @Value("注入普通属性")
    private String str;

    @Value("${jdbc.driverClassName}")
    private String driver;

    /**
     * 初始化方法
     */
    @PostConstruct
    public void init() {
        System.out.println("初始化方法");
    }

    /**
     * 销毁方法
     */
    @PreDestroy
    public void destroy() {
        System.out.println("销毁方法");
    }

//    public void setAccountDao(AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }

    @Override
    public List<Account> findAll() {
        System.out.println("str = " + str);
        System.out.println("driver = " + driver);
        return accountDao.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public void save(Account account) {
        accountDao.save(account);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void delete(Integer id) {
        accountDao.delete(id);
    }
}
