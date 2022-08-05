package service.impl;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import service.IUserService;
import utils.BeanFactory;

/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */
public class UserServiceImpl implements IUserService {
    @Override
    public void save() {
        System.out.println("service.save");

        // 调用dao层传统方式，存在编译期依赖，耦合重
//        IUserDao userDao = new UserDaoImpl();

        try {
            // 反射，解决编译期依赖 硬编码
//            IUserDao userDao = (IUserDao) Class.forName("dao.impl.UserDaoImpl").newInstance();

            IUserDao userDao = (IUserDao) BeanFactory.getBean("userDao");
            userDao.save();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
