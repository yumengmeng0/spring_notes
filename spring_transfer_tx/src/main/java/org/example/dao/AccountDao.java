package org.example.dao;

import org.example.domain.Account;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description:
 */
public interface AccountDao {

    /**
     * 转出
     *
     * @param outUser
     * @param money
     */
    public void out(Integer outUser, Double money);

    /**
     * 转入
     *
     * @param inUser
     * @param money
     */
    public void in(Integer inUser, Double money);
}
