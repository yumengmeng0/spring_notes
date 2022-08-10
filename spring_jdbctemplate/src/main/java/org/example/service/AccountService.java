package org.example.service;

import org.example.domain.Account;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description:
 */
public interface AccountService {

    /**
     * 查询所有账户
     *
     * @return
     */
    public List<Account> findAll();

    /**
     * 根据id查找账户
     *
     * @param id
     * @return
     */
    public Account findById(Integer id);

    /**
     * 添加账户
     *
     * @param account
     */
    public void save(Account account);

    /**
     * 更新账户
     *
     * @param account
     */
    public void update(Account account);

    /**
     * 根据id删除账户
     *
     * @param id
     */
    public void delete(Integer id);
}
