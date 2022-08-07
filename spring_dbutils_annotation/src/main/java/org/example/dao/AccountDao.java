package org.example.dao;



import org.example.domain.Account;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/6
 * @version: 1.0.0
 * @description:
 */
public interface AccountDao {
    public List<Account> findAll();

    public Account findById(Integer id);

    public void save(Account account);

    public void update(Account account);

    public void delete(Integer id);
}
