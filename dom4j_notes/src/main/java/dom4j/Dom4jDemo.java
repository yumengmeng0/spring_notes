package dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import utils.BeanFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * @author: ymm
 * @date: 2022/8/5
 * @version: 1.0.0
 * @description:
 */
public class Dom4jDemo {

    public Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }


    public static void main(String[] args) {

        InputStream inputStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        SAXReader saxReader = new SAXReader();
        try {
            // xml文件对象
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            System.out.println("rootElement = " + rootElement);
            System.out.println("rootElement.getName() = " + rootElement.getName());
            List<Node> nodeList = rootElement.selectNodes("//beans");
            System.out.println("nodeList = " + nodeList);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Dom4jDemo dom4jDemo = new Dom4jDemo();

    }

}
