package org.fan.core;

import java.sql.Connection;

/**
 * managed事务管理器  此处不再实现
 *
 * @author fanshuaiyao
 * @version 1.0
 */
public class ManagedTransaction implements Transaction {
    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public void close() {

    }

    @Override
    public void openConnection() {
    }

    @Override
    public Connection getConnection() {
        return null;
    }
}
