package test;

import org.example.config.SpringConfig;
import org.example.domain.Account;
import org.example.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/7
 * @version: 1.0.0
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class AccountServiceTest {
    //    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    @Autowired
    AccountService accountService;

    @Test
    public void findAll() {
        List<Account> accountList = accountService.findAll();

        for (Account account : accountList) {
            System.out.println("account = " + account);
        }

    }

    @Test
    public void save() {
        Account account = new Account();

        account.setName("lisi");
        account.setMoney(6666d);
        accountService.save(account);
    }

    @Test
    public void findById() {
        Account account = accountService.findById(1);
        System.out.println("account = " + account);
    }

    @Test
    public void delete() {
        accountService.delete(3);
    }

    @Test
    public void update() {
        Account account = new Account();
        account.setId(1);
        account.setName("wangwu");
        account.setMoney(999.33);

        accountService.update(account);
    }

}
