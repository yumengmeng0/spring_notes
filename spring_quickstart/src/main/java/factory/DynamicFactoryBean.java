package factory;

import dao.IUserDao;
import dao.impl.UserDaoImpl;

/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */
public class DynamicFactoryBean {

    public IUserDao createUserDao() {
        return new UserDaoImpl();
    }
}
