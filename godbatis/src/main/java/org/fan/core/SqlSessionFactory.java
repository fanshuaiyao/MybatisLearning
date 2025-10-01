package org.fan.core;

import java.util.Map;

/**
 * SqlSessionFactor获取会话对象
 * 一个数据库一个SqlSessionFactor对象
 * 一个SqlSessionFactor可以开启多个会话SqlSession
 *
 * @author fanshuaiyao
 * @version 1.0
 */
public class SqlSessionFactory {
    /**
     * 事务管理器
     * 面向接口编程  灵活切换  跟俊配置文件 灵活的去实例化对象的事务管理器
     */
    private Transaction transaction;

    /**
     * 存在sql语句的集合  key：sqlId  value：mappedStatement
     */
    private Map<String, MappedStatement> mappedStatements;


    public SqlSessionFactory(Transaction transaction, Map<String, MappedStatement> mappedStatements) {
        this.transaction = transaction;
        this.mappedStatements = mappedStatements;
    }

    public SqlSessionFactory() {
    }


    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Map<String, MappedStatement> getMappedStatements() {
        return mappedStatements;
    }

    public void setMappedStatements(Map<String, MappedStatement> mappedStatements) {
        this.mappedStatements = mappedStatements;
    }

    /**
     * 获取sql会话对象
     *
     * @return
     */
    public SqlSession openSession() {
        // 开启会话就是开启连接  连接打开了
        transaction.openConnection();

        SqlSession sqlSession = new SqlSession(this);

        return sqlSession;
    }


}
