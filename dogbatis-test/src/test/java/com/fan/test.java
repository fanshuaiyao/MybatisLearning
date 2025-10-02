package com.fan;

import org.fan.core.SqlSession;
import org.fan.core.SqlSessionFactory;
import org.fan.core.SqlSessionFactoryBuilder;
import org.fan.pojo.User;
import org.fan.utils.Resources;
import org.junit.Test;

public class test {

    @Test
    public void testInsertUser(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.builder(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sessionFactory.openSession();

        User user = new User("77","jack", "88");
        int count = sqlSession.insert("user.insertUser", user);
        System.out.println(count);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectById(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.builder(Resources.getResourceAsStream("godbatis-config.xml"));
        SqlSession sqlSession = sessionFactory.openSession();

        Object object = sqlSession.selectOne("user.selectById", "77");
        System.out.println(object);
        sqlSession.commit();
        sqlSession.close();
    }

}
