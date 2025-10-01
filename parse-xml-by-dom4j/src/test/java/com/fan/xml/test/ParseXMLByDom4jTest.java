package com.fan.xml.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;

public class ParseXMLByDom4jTest {

    @Test
    public void testParseMyBatisConfigXML() throws DocumentException {
        // 获取SAXReader 对象
        SAXReader reader = new SAXReader();

        // 获取输入流
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");

        // 读取xml文件,获取document对象 代表整个xml文件
        Document document = reader.read(is);

        // 获取文档中的根标签
//        Element rootElement = document.getRootElement();
//        String name = rootElement.getName();
//        System.out.println(name);

        // 获取环境的标签id
        String xpath = "/configuration/environments"; // 标签路径匹配 快速定位标签元素
        Element environments = (Element) document.selectSingleNode(xpath); // Element 是node的子类
        String attributeValue = environments.attributeValue("default");

        // 根据默认的环境的值 获取对应的环境的标签 default -> environment
        // 通过@来指定
        String xpath1 = "/configuration/environments/environment[@id='"+attributeValue+"']";
        Element environment = (Element) document.selectSingleNode(xpath1);
    }
}
