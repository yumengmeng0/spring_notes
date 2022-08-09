package test;

import org.example.config.SpringConfig;
import org.example.dao.AccountDao;
import org.example.proxy.CglibProxyFactory;
import org.example.proxy.JDKProxyFactory;
import org.example.service.AccountService;
import org.example.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class AccountTest {

    @Autowired
    AccountService accountService;
    @Autowired
    private JDKProxyFactory jdkProxyFactory;
    @Autowired
    private CglibProxyFactory cglibProxyFactory;

    @Test
    public void transfer() {
//        AccountService accountService = new AccountServiceImpl();
        accountService.transfer("1", "2", 100d);
    }

    @Test
    public void transferJDKProxy() {
        AccountService accountServiceJDKProxy = jdkProxyFactory.createAccountServiceJDKProxy();
//        accountServiceJDKProxy.transfer("1", "2", 100d);
        accountServiceJDKProxy.save();
    }

    @Test
    public void cglibProxyFactory(){
        AccountService accountServiceCglibProxy = cglibProxyFactory.createAccountServiceCglibProxy();
        accountServiceCglibProxy.transfer("2", "1", 100d);
    }

}
