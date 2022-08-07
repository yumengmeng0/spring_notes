package test;


import dao.IUserDao;
import domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import service.IUserService;
import service.impl.UserServiceImpl;

import javax.sql.rowset.spi.XmlReader;


/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */
public class SpringTest {

    @Test
    public void save() {
        // 获取Spring上下文对象，借助上下文对象获取IOC容器中的bean对象
        // 加载同时创建对象到容器
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        ApplicationContext context = new FileSystemXmlApplicationContext("E:\\GitHub\\spring_notes\\spring_quickstart\\src\\main\\resources\\applicationContext.xml");

        // 1.根据bean id查找对应bean对象
//        IUserDao userDao = (IUserDao) context.getBean("userDao");
        // 2.根据类型查找：匹配多个实例会报错
//        IUserDao userDao = (IUserDao) context.getBean(IUserDao.class);
        IUserDao userDao = (IUserDao) context.getBean("userDao2", IUserDao.class);
        userDao.save();
    }

    @Test
    public void save2() {

        // 核心接口，不会创建对象到容器，调用getBean方法时创建对象
        BeanFactory xmlBeanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));

        IUserDao userDao = (IUserDao) xmlBeanFactory.getBean("userDao");

        userDao.save();
    }

    /**
     * 测试bean的scope属性
     */
    @Test
    public void beanScope() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // scope="singleton"
        IUserDao userDao = (IUserDao) context.getBean("userDao");
        IUserDao userDao2 = (IUserDao) context.getBean("userDao");
        System.out.println("userDao2==userDao = " + (userDao2 == userDao)); // true

//        scope="prototype"
        IUserDao userDao21 = (IUserDao) context.getBean("userDao2");
        IUserDao userDao22 = (IUserDao) context.getBean("userDao2");
        System.out.println("(userDao22==userDao21) = " + (userDao22 == userDao21)); // false

        context.close();
    }

    @Test
    public void service() {
//        IUserService userService = new UserServiceImpl();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IUserService userService = (IUserService) context.getBean("userService");
        userService.save();
    }

    @Test
    public void user(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml","applicationContext-dao.xml");
        User user = (User) context.getBean("user");
        System.out.println("user = " + user);
    }


}
