package org.fan.core.transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * JDBC事务管理器
 *
 * @author fanshuaiyao
 * @version 1.0
 */
public class JDBCTransaction implements Transaction {

    /**
     * 数据源属性
     */
    private DataSource dataSource;

    /**
     * 自动提交标记
     */
    private boolean isAutoCommit;

    /**
     * 连接对象
     */
    private Connection connection;

    /**
     * 创建事务管理器对象
     *
     * @param dataSource
     * @param isAutoCommit
     */
    public JDBCTransaction(DataSource dataSource, boolean isAutoCommit) {
        this.dataSource = dataSource;
        this.isAutoCommit = isAutoCommit;
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 确保同一个sql对话中的conn对象是同一个
     */
    public void openConnection(){
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                // 开启事务
                connection.setAutoCommit(isAutoCommit);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
