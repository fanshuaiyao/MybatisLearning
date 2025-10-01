package com.fan.godbatis.test;

import com.fan.godbatis.pojo.User;
import org.fan.core.SqlSession;
import org.fan.core.SqlSessionFactory;
import org.fan.core.SqlSessionFactoryBuilder;
import org.fan.utils.Resources;
import org.junit.Test;

public class GodBatisTest {
    @Test
    public void testSqlSessionFactory(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.builder(Resources.getResourceAsStream("godbatis-config.xml"));
        System.out.println(sessionFactory);
    }

    @Test
    public void testInsertUser(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.builder(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sessionFactory.openSession();

        User fansy = new User("1", "fansy", "12");
        int count = sqlSession.insert("user.insertUser", fansy);
        System.out.println(count);

        sqlSession.commit();
        sqlSession.close();
    }
}
