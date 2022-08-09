import org.example.config.SpringConfig;
import org.example.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: ymm
 * @date: 2022/8/9
 * @version: 1.0.0
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void transfer() {
        accountService.transfer();
    }

}
