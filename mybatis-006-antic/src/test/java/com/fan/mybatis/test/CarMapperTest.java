package com.fan.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.fan.mybatis.mapper.CarMapper;
import org.fan.mybatis.pojo.Car;
import org.fan.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectByType(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);


        List<Car> carList = mapper.selectBytype("油车");
        carList.forEach(System.out::println);
        sqlSession.close();
    }
}
