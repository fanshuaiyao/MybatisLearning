package com.fan.bank.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * mybatis工具类
 * @author fanshuaiyao
 * @version 1.0
 */
public class SqlSessionUtil {

    private static final SqlSessionFactory sqlSessionFactory;

    private SqlSessionUtil(){}

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取会话对象
     * @return 会话对象
     */
    public static SqlSession openSqlsession(){
        return sqlSessionFactory.openSession();
    }
}
