package com.fan.mybatis.utils;

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

    private static SqlSessionFactory sqlSessionFactory;
    // 工具类的构造方法一般都是私有的
    // 工具类的方法一般都是静态的，采用类名直接调用方法
    // 为了防止new对象，构造方法私有化
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
