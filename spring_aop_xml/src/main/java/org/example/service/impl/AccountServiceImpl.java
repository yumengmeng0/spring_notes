package org.example.service.impl;

import org.example.service.AccountService;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description:
 */
public class AccountServiceImpl implements AccountService {
    /**
     * 目标方法，切入点
     */
    @Override
    public void transfer() {
        int i = 1 / 0;
        System.out.println("transfer");
    }
}
