package org.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description: 连接工具类，从数据源中获取一个连接，并且将获取的连接与线程进行绑定
 * <p>
 * ThreadLocal：线程内部的存储类，可以在指定线程内存储数据
 * key：threadLocal
 * value：任意数据类型的值
 */
@Component
public class ConnectionUtils {
    @Autowired
    private DataSource dataSource;

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 获取当前线程绑定的连接，如果获取到的连接为空，那么就要从数据源中获取连接，
     * 并放到ThreadLocal中（绑定到当前线程）
     *
     * @return
     */
    public Connection getThreadConnection() {

        // 1.先从ThreadLocal上获取连接
        Connection connection = threadLocal.get();

        // 2.判断当前线程是否存在Connection对象
        if (connection == null) {
            // 3.从数据源中获取一个连接，并且存入ThreadLocal中
            try {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    /**
     * 解除当前线程的连接绑定
     */
    public void removeThreadConnection() {
        threadLocal.remove();
    }
}
