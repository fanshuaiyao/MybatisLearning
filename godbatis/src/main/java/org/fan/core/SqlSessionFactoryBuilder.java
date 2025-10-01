package org.fan.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fan.core.Constant.*;

/**
 * SqlSessionFactory构造器
 * 通过这个构造器的builder方法获取SqlSessionFactory对象
 *
 * @author fanshuaiyao
 * @version 1.0
 */
public class SqlSessionFactoryBuilder {

    /**
     * 无参构造
     */
    public SqlSessionFactoryBuilder() {
    }

    /**
     * 解析mybatis-config.xml文件  构建SqlSessionFactor对象
     *
     * @param inputStream 指向mybatis-config.xml文件的输入流
     * @return sqlSessionFactory对象
     */
    public SqlSessionFactory builder(InputStream inputStream) {


        SqlSessionFactory sqlSessionFactory = null;

        try {
            // 解析xml文件
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element environments = (Element) document.selectSingleNode("/configuration/environments");
            String defaultId = environments.attributeValue("default");


            Element environmentElt = (Element) document.selectSingleNode("/configuration/environments/environment[@id='" + defaultId + "']");

            Element transactionEtl = environmentElt.element("transactionManager");
            Element dataSourceEtl = environmentElt.element("dataSource");

            // 获取数据源
            DataSource dataSource = getDataSource(dataSourceEtl);

            // 获取事务管理器
            Transaction transaction = getTransaction(transactionEtl, dataSource);

            // 获取mappedStatements
            Map<String, MappedStatement> mappedStatements = null;

            // 解析文件  然后创建sqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactory(transaction, mappedStatements);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return sqlSessionFactory;
    }

    /**
     * 获取事务管理器
     *
     * @param transactionEtl 事务管理器标签
     * @param dataSource     数据源
     * @return 事务管理器
     */
    private Transaction getTransaction(Element transactionEtl, DataSource dataSource) {

        Transaction transaction = null;
        String type = transactionEtl.attributeValue("type").trim().toUpperCase();

        if (type.equals(JDBC_TRANSACTION)) {
            transaction = new JDBCTransaction(dataSource, false); // 默认开启事务，需要手动提交
        }
        if (type.equals(MANAGED_TRANSACTION)) {
            transaction = new ManagedTransaction();
        }
        return transaction;
    }

    /**
     * 获取数据源
     *
     * @param dataSourceEtl 数据源标签
     * @return 数据源
     */
    private DataSource getDataSource(Element dataSourceEtl) {

        HashMap<String, String> map = new HashMap<>();
        List<Element> elementList = dataSourceEtl.elements("property");
        elementList.forEach(property -> {
            String name = property.attributeValue("name");
            String value = property.attributeValue("value");
            map.put(name, value);
        });


        DataSource dataSource = null;
        String type = dataSourceEtl.attributeValue("type").trim().toUpperCase();
        if (type.equals(UN_POOLED)) {
            dataSource = new UnPooledDataSource(map.get("driver"), map.get("url"), map.get("username"), map.get("password"));
        }
        if (type.equals(POOLED)) {
            dataSource = new PooledDataSource();
        }
        if (type.equals(JNDI)) {
            dataSource = new JNDIDataSource();
        }

        return dataSource;
    }

}
