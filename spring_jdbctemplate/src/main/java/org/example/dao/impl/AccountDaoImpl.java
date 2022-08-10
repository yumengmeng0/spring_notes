package org.example.dao.impl;

import org.example.dao.AccountDao;
import org.example.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description:
 */
@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有账户
     *
     * @return
     */
    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        List<Account> accountList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Account.class));
        return accountList;
    }

    /**
     * 根据id查找账户
     *
     * @param id
     * @return
     */
    @Override
    public Account findById(Integer id) {
        String sql = "select * from account where id = ?";
        Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), id);
        return account;
    }

    /**
     * 添加账户
     *
     * @param account
     */
    @Override
    public void save(Account account) {
        String sql = "insert into account(name, money) values(?, ?)";
        jdbcTemplate.update(sql, account.getName(), account.getMoney());
    }

    /**
     * 更新账户
     *
     * @param account
     */
    @Override
    public void update(Account account) {
        String sql = "update account set name = ?, money = ? where id = ?";
        jdbcTemplate.update(sql, account.getName(), account.getMoney(), account.getId());
    }

    /**
     * 根据id删除账户
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        String sql = "delete from account where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
