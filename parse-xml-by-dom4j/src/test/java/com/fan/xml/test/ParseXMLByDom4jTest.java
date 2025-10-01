package com.fan.xml.test;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

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
        // Element rootElement = document.getRootElement();
        // String name = rootElement.getName();
        // System.out.println(name);

        // 获取环境的标签id
        String xpath = "/configuration/environments"; // 标签路径匹配 快速定位标签元素
        Element environments = (Element) document.selectSingleNode(xpath); // Element 是node的子类
        // 获取属性的值
        String defaultEnvironmentId = environments.attributeValue("default");
        System.out.println("默认的环境id：" + defaultEnvironmentId);

        // 根据默认的环境的值 获取对应的环境的标签 default -> environment
        // 通过@来指定
        String xpath1 = "/configuration/environments/environment[@id='" + defaultEnvironmentId + "']";
        Element environment = (Element) document.selectSingleNode(xpath1);


        //  继续获取environment的子节点 事务管理器的类型
        Element transactionManager = environment.element("transactionManager");
        String transactionType = transactionManager.attributeValue("type");
        System.out.println("事务管理器类型：" + transactionType);

        // 获取datasource节点
        Element dataSource = environment.element("dataSource");
        String dataSourceType = dataSource.attributeValue("type");
        System.out.println("数据源类型：" + dataSourceType);

        // 获取datasource下的property节点
        List<Element> propertiesList = dataSource.elements();
        propertiesList.forEach(item -> {
            String name = item.attributeValue("name");
            String value = item.attributeValue("value");
            System.out.println(name + "=" + value);
        });


        // 获取所有的mapper
        String xpath2 = "//mapper";
        List<Node> mappers = document.selectNodes(xpath2);
        mappers.forEach(mapper -> {
            Element mapperElt = (Element) mapper;
            String resource = mapperElt.attributeValue("resource");
            System.out.println("mapper的resource：" + resource);

        });

    }

    @Test
    public void testParseMybatisCarMapperXML() throws DocumentException {
        // 获取一个SAXRead对象
        SAXReader reader = new SAXReader();

        // 创建一个输入流 读取mapper文件
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("CarMapper.xml");

        // 利用read对象读取信息 获取xml文件的映射document对象
        Document document = reader.read(is);

        // 利用xpath获取mapper中的属性
        String xpath = "/mapper";
        Element mapper = (Element) document.selectSingleNode(xpath);
        String namespace = mapper.attributeValue("namespace");
        System.out.println("namespace=" + namespace);

        // 获取mapper下所有的子节点
        for (Element element : mapper.elements()) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType"); // 没有这个属性  返回null
            System.out.println("标签id=" + id);
            System.out.println("返回类型=" + resultType);
            // 获取sql语句
            String sql = element.getTextTrim();
            System.out.println("sql语句=" + sql);

            // INSERT INTO t_car (id,car_num, brand, guide_price, produce_time, car_type) VALUES (null, #{carNum},#{brand}, #{guidePrice}, #{produceTime}, #{carType});
            //INSERT INTO t_car (?,?,?,?,?);
            String newSql = sql.replaceAll("#\\{[0-9A-Za-z$_]*}","?");
            System.out.println("newSql语句=" + newSql);
        }

    }
}





