package com.fan.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.fan.mybatis.mapper.CarMapper;
import org.fan.mybatis.pojo.Car;
import org.fan.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

import java.util.List;

public class CarTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
//        List<Car> cars = mapper.selectByMultCondition("Audi",30000L,"新能源");
        List<Car> cars = mapper.selectByMultCondition("Audi",20000L,"新能源");
        cars.forEach(System.out::println);
        sqlSession.close();
    }
}
