package org.example.service;

/**
 * @author: ymm
 * @date: 2022/8/7
 * @version: 1.0.0
 * @description:
 */
public interface AccountService {

    /**
     * 转账
     *
     * @param outUser
     * @param inUser
     * @param money
     */
    public void transfer(String outUser, String inUser, Double money);

}
