package org.example.dao;

import java.sql.SQLException;

/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description:
 */
public interface AccountDao {

    /**
     * 转出操作
     *
     * @param outUser
     * @param money
     */
    public void out(String outUser, Double money);

    /**
     * 转入操作
     *
     * @param inUser
     * @param money
     */
    public void in(String inUser, Double money);
}
