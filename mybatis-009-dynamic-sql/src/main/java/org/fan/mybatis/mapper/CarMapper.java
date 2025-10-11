package org.fan.mybatis.mapper;

import org.fan.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {

    Car selectCarById(Long id);

    List<Car> selectAll();

    /**
     * 使用resultMap
     * @return
     */
    List<Car> selectAllByResultMap();

    Long selectTotalCount();
}
