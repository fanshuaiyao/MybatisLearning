package org.fan.core;

import java.io.InputStream;

/**
 * SqlSessionFactory构造器
 * 通过这个构造器的builder方法获取SqlSessionFactory对象
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
    public SqlSessionFactor builder(InputStream inputStream) {

        return null;
    }

}
