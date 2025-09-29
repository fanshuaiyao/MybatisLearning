package com.fan.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisIntroduction {
    public static void main(String[] args) throws Exception {
        // 1.获取sqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 2.获取sqlSessionFactory  一般一个数据库对应一个sqlSessionFactory对象
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        // 3.获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        // 4.执行SQL语句
        int i = sqlSession.insert("insertCar");
        int deleteCarById = sqlSession.delete("deleteCarById",6);


        System.out.println("插入了条记录" + i);
        System.out.println("删除了条记录" + deleteCarById);

        // 5. sqlSession不支持自动提交，所以需要手动提交
        sqlSession.commit();


    }
}
