package service.impl;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.IUserService;

/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    // 必须有无参构造
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        userDao.save();
    }
}
