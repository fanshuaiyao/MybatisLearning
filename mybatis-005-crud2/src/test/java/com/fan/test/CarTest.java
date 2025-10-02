package com.fan.test;


import org.apache.ibatis.session.SqlSession;
import org.fan.mapper.CarMapper;
import org.fan.pojo.Car;
import org.fan.utils.SqlSessionUtil;
import org.junit.Test;

import java.util.List;

public class CarTest {
    
    @Test
    public void testInsert() {
        // 测试新增功能
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null, "8654", "凯美瑞", 3.0, "2000-1-16", "燃油车");
        mapper.insert(car);
        sqlSession.commit();
        sqlSession.close();
    }
    
    @Test
    public void testSelectAll() {
        // 测试查询所有记录功能
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectAll();
        System.out.println(cars);
        sqlSession.close();
    }
    
    @Test
    public void testDelete() {
        // 测试删除功能
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        mapper.deleteById(1L); // 假设要删除id为1的记录
        sqlSession.commit();
        sqlSession.close();
    }
    
    @Test
    public void testUpdate() {
        // 测试更新功能
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(1L, "8654", "凯美瑞", 3.0, "2000-1-16", "燃油车"); // 假设id为1的记录
        mapper.update(car);
        sqlSession.commit();
        sqlSession.close();
    }
    
    @Test
    public void testSelect() {
        // 测试查询功能
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.selectById(1L); // 假设查询id为1的记录
        sqlSession.close();
    }
}
