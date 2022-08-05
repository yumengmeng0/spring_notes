package dao.impl;

import dao.IUserDao;

import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */

public class UserDaoImpl implements IUserDao {

    public void init() {
        System.out.println("初始化方法");
    }

    public void destroy() {
        System.out.println("销毁方法");
    }

    @Override
    public void save() {

        System.out.println("UserDaoImpl.save");
    }
}
