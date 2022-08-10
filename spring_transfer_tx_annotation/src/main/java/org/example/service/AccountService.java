package org.example.service;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description:
 */
public interface AccountService {

    /**
     * 转账
     *
     * @param outUser 转出账户id
     * @param inUser 转入账户id
     * @param money
     */
    public void transfer(Integer outUser, Integer inUser, Double money);

}
