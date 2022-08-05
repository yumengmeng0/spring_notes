package test;

import org.junit.Test;
import service.IUserService;
import service.impl.UserServiceImpl;

/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */
public class SpringTest {

    @Test
    public void save() {
        IUserService userService = new UserServiceImpl();
        userService.save();
    }
}
