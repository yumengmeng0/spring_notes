package org.example.dao.impl;

import org.example.dao.AccountDao;
import org.apache.commons.dbutils.QueryRunner;
import org.example.utils.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description:
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private QueryRunner queryRunner;

    @Autowired
    private ConnectionUtils connectionUtils;

    /**
     * 转出操作
     *
     * @param outUser
     * @param money
     */
    @Override
    public void out(String outUser, Double money) {
        String sql = "update account set money = money - ? where id = ?";

        // todo 在这里try会不会被AccountServiceImpl捕获存疑，在juint测试时，会继续传递，但Java单独测试，不会向外部传递Exception
        // 需要改造一下，这里的异常应该抛出由service层处理
        try {
            queryRunner.update(connectionUtils.getThreadConnection(), sql, money, outUser);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("out"); // 上面发生除0异常，这里不会被执行，捕获的是sql异常
        }
    }

    /**
     * 转入操作
     *
     * @param inUser
     * @param money
     */
    @Override
    public void in(String inUser, Double money) {
        String sql = "update account set money = money + ? where id = ?";

        try {
            queryRunner.update(connectionUtils.getThreadConnection(), sql, money, inUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
