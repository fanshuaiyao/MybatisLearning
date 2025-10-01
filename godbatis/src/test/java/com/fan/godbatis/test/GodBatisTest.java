package com.fan.godbatis.test;

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
}
