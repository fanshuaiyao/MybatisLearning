package org.fan.core;

import java.util.Map;

/**
 * SqlSessionFactor获取会话对象
 * 一个数据库一个SqlSessionFactor对象
 * 一个SqlSessionFactor可以开启多个会话SqlSession
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
     * 数据源
     */
    private

    /**
     * 存在sql语句的集合  key：sqlId  value：mappedStatement
     */
    private Map<String, MappedStatement> mappedStatements;
}
