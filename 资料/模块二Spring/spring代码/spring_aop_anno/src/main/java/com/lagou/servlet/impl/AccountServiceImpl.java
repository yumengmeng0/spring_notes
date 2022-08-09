package com.lagou.servlet.impl;

import com.lagou.servlet.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {


    public void transfer() {
        int i = 1 / 0;
        System.out.println("转账方法执行了....");
    }
}
