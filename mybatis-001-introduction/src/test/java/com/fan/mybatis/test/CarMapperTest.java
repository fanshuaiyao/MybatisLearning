package com.fan.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class CarMapperTest {

    @Test
    public void testInsertCar(){
        SqlSession sqlSession = null;
        try {
            // 获取sqlSession
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            sqlSession = sqlSessionFactory.openSession();

            // 业务
            int count = sqlSession.insert("insertCar");
            System.out.println("insert into number = " + count);

            // commit
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null){
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally{
            if (sqlSession != null){
                sqlSession.close();
            }
        }
    }
}
