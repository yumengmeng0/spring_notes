package org.example.dao.impl;

import org.example.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
     * 转出
     *
     * @param outUser
     * @param money
     */
    @Override
    public void out(Integer outUser, Double money) {
        String sql = "update account set money = money - ? where id = ?";
        jdbcTemplate.update(sql, money, outUser);
    }

    /**
     * 转入
     *
     * @param inUser
     * @param money
     */
    @Override
    public void in(Integer inUser, Double money) {
        String sql = "update account set money = money + ? where id = ?";
        jdbcTemplate.update(sql, money, inUser);
    }
}
