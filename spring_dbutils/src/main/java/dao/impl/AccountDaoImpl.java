package dao.impl;

import dao.AccountDao;
import domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/6
 * @version: 1.0.0
 * @description:
 */

public class AccountDaoImpl implements AccountDao {

    private QueryRunner queryRunner;

    public void setQueryRunner(QueryRunner queryRunner) {
        this.queryRunner = queryRunner;
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        List<Account> accountList = null;

        try {
            accountList = queryRunner.query(sql, new BeanListHandler<Account>(Account.class));
            return accountList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    @Override
    public Account findById(Integer id) {
        String sql = "select * from account where id = ?";
        Account account = null;

        try {
            account = queryRunner.query(sql, new BeanHandler<>(Account.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public void save(Account account) {
        String sql = "insert into account(name, money) values(?, ?)";
        Object[] param = {
                account.getName(), account.getMoney()
        };

        try {
            int update = queryRunner.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
        String sql = "update account set name = ?, money = ? where id = ?";
        Object[] param = {account.getName(), account.getMoney(), account.getId()};

        try {
            queryRunner.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from account where id = ?";
        try {
            queryRunner.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
