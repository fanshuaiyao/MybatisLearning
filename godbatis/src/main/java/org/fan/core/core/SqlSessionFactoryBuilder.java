package org.fan.core.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.fan.core.dataSource.JNDIDataSource;
import org.fan.core.dataSource.PooledDataSource;
import org.fan.core.dataSource.UnPooledDataSource;
import org.fan.core.statement.MappedStatement;
import org.fan.core.transaction.JDBCTransaction;
import org.fan.core.transaction.ManagedTransaction;
import org.fan.core.transaction.Transaction;
import org.fan.utils.Resources;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.fan.core.constans.Constant.*;

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

            // 这个list集合存放着mapper的路径
            List<String> sqlMapperXmlPathList = new ArrayList<>();
            List<Node> list = document.selectNodes("//mapper");
            list.forEach(node ->{
                Element element = (Element) node;
                String resource = element.attributeValue("resource");
                sqlMapperXmlPathList.add(resource);
            });

            // 获取数据源
            DataSource dataSource = getDataSource(dataSourceEtl);

            // 获取事务管理器
            Transaction transaction = getTransaction(transactionEtl, dataSource);

            // 获取mappedStatements
            Map<String, MappedStatement> mappedStatements = getMappedStatements(sqlMapperXmlPathList);

            // 解析文件  然后创建sqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactory(transaction, mappedStatements);
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return sqlSessionFactory;
    }

    /**
     * 解析所有的mapper文件 构建mapper结合
     * @param sqlMapperXmlPathList mapper文件路径集合
     * @return 集合
     */
    private Map<String, MappedStatement> getMappedStatements(List<String> sqlMapperXmlPathList) {

        // 存放所有的mapper文件中的sql语句标签
        HashMap<String, MappedStatement> mappedStatementsMap = new HashMap<>();

        sqlMapperXmlPathList.forEach(sqlMapperXmlPath -> {
            try {
                SAXReader reader = new SAXReader();
                Document document = reader.read(Resources.getResourceAsStream(sqlMapperXmlPath));
                // 开始解析mapper.xml文件
                Element mapper = (Element) document.selectSingleNode("mapper");

                // 获取namespace
                String namespace = mapper.attributeValue("namespace");
                // 获取所有的子节点
                List<Element> elementList = mapper.elements();
                elementList.forEach(element -> {
                    String id = element.attributeValue("id");
                    // 进行拼接 生成最终的sqlId
                    String sqlId = namespace + "." + id;
                    String resultType = element.attributeValue("resultType");
                    // 获取sql语句
                    String sql = element.getTextTrim();
                    // 封装标签对象
                    MappedStatement mappedStatement = new MappedStatement(resultType, sql);

                    // 将封装好的标签对象放入map中
                    mappedStatementsMap.put(sqlId, mappedStatement);
                });

            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        });



        return mappedStatementsMap;

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
