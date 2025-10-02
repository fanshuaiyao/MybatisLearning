package org.fan.core.dataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * 数据源的实现类 UNPOOLED  不使用连接池 每一次都新建conn对象
 * 所有数据源必须实现 javax.sql.DataSource这个接口，这是JDK的规定
 *
 * @author fanshuaiyao
 * @version 1.0
 */
public class UnPooledDataSource implements javax.sql.DataSource {

    private String url;
    private String userName;
    private String passWord;

    /**
     * 创建一个数据源对象
     *
     * @param driver   数据 驱动
     * @param url      连接地址
     * @param userName 用户名
     * @param passWord 用户密码
     */
    public UnPooledDataSource(String driver, String url, String userName, String passWord) {
        // 直接注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.url = url;
        this.userName = userName;
        this.passWord = passWord;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, passWord);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
