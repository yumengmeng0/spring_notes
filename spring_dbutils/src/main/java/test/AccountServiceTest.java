package test;

import domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.AccountService;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/7
 * @version: 1.0.0
 * @description:
 */
public class AccountServiceTest {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    AccountService accountService = (AccountService) context.getBean("accountService");

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
