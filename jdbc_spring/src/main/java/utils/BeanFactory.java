package utils;

import dao.IUserDao;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */
public class BeanFactory {

    static Map<String, Object> iocMap = new HashMap<>();

    // 程序启动是初始化对象实例
    static {
        // 1.读取配置文件
        InputStream resource = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        // 2.解析xml（dom4j）
        try {
            Document document = saxReader.read(resource);

            // 3.编写xpath表达式
            String xpath = "//bean";

            // 4.获取所有bean标签
/*
             dom4j-1.6.1 返回List<Element>
             List selectNodes(String xpathExpression);
             2.1.3
             List<Node> selectNodes(String xpathExpression);
             Element是Node的子接口
*/
//            List<Element> elementList = document.selectNodes(xpath);

            Element rootElement = document.getRootElement();
            List<Element> elementList = rootElement.elements();

            // 5.遍历并使用反射创建对象实例，存到map集合中
            for (Element element : elementList) {
                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                System.out.println("className = " + className);
                Object o = Class.forName(className).getConstructor().newInstance();
                System.out.println("o = " + o);
                iocMap.put(id, o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过bean的id获取bean的对象
     *
     * @param beanId
     * @return
     */
    public static Object getBean(String beanId) {
        return iocMap.get(beanId);
    }


    public static void main(String[] args) {
        BeanFactory beanFactory = new BeanFactory();
        IUserDao userDao = (IUserDao) iocMap.get("userDao");
        userDao.save();
    }
}
