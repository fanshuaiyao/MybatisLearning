package com.fan.mybatis.test;

import com.fan.mybatis.pojo.Car;
import com.fan.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {

    @Test
    public void selectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        List<Car> cars = sqlSession.selectList("selectAll");
        cars.forEach(System.out::println);
        sqlSession.close();
    }
    @Test
    public void selectById(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();

        Object car = sqlSession.selectOne("selectById", 19);
        System.out.println(car);

        sqlSession.close();
    }

    @Test
    public void updateById(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        Car car = new Car(19L, "4444", "audi7", 40.0, "2001-01-02", "氢能源车");


        int updateById = sqlSession.update("updateById", car);
        System.out.println(updateById);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteById(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();

        int deleteById = sqlSession.delete("deleteById", 17);
        System.out.println(deleteById);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCarByPOJO(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();

        Car car = new Car(null, "3333", "audi", 30.0, "200-01-02", "油车");
        int count = sqlSession.insert("insertCar", car);
        System.out.println(count);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsertCar(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        int count = sqlSession.insert("insertCar");
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
