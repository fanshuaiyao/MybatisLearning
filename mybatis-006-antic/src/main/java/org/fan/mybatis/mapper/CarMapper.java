package org.fan.mybatis.mapper;


import org.fan.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {
    /**
     * 插入一条汽车记录
     * @param car 汽车对象
     * @return 受影响的行数
     */
    int insert(Car car);

    /**
     * 更新汽车信息
     * @param car 汽车对象
     * @return 受影响的行数
     */
    int update(Car car);

    /**
     * 根据ID删除汽车记录
     * @param id 汽车ID
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据ID查询汽车信息
     * @param id 汽车ID
     * @return 汽车对象
     */
    Car selectById(Long id);

    /**
     * 查询所有汽车信息
     * @return 汽车对象列表
     */
    List<Car> selectAll();

    List<Car> selectBytype(String type);

    int deleteBatch(String ids);

    List<Car> selectByBrandLike(String brand);

    int insertCarUserGeneratedKeys(Car car);

}
