package org.fan.core.transaction;

import java.sql.Connection;

/**
 * 事务管理器接口
 * 所有的事务都应该实现这个接口
 * @author fanshauiyao
 * @version 1.0
 */
public interface Transaction {
    // 提供控制事务的方法

    /**
     * 提交事务
     */
    void commit();

    /**
     * 回滚事务
     */
    void rollback();

    /**
     * 关闭事务
     */
    void close();

    /**
     * 开启事务连接
     */
    void openConnection();

    /**
     * 获取连接对象
     */
    Connection getConnection();


}
