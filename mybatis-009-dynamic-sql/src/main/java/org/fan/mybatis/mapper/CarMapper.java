package org.fan.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.fan.mybatis.pojo.Car;

import java.util.List;

public interface CarMapper {

    /**
     * 多条件查询
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultCondition(@Param("brand") String brand, @Param("guidePrice") Long guidePrice, @Param("carType") String carType);

}
