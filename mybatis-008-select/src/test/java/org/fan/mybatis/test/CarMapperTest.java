package org.fan.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.fan.mybatis.mapper.CarMapper;
import org.fan.mybatis.pojo.Car;
import org.fan.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectCarById(1L);
        System.out.println(car);
        sqlSession.close();
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectAllByResultMap(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAllByResultMap();
        cars.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void testSelectTotalCount(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Long totalCount = mapper.selectTotalCount();
        System.out.println(totalCount);
        sqlSession.close();
    }

}
