package dao.impl;

import dao.IUserDao;

/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */

public class UserDaoImpl2 implements IUserDao {
    @Override
    public void save() {
        System.out.println("UserDaoImpl.save");
    }
}
