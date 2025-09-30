package com.fan.mybatis.test;

import com.fan.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CarMapperTest {

    @Test
    public void testInsertCar(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        int count = sqlSession.insert("insertCar");
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
