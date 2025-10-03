package org.fan.mybatis.mapper;

import org.fan.mybatis.pojo.Student;

import java.util.Map;

public interface StudentMapper {

    /**
     * 当方法中的参数只有一个 且都是简单的参数
     */
    Student selectById(Long id);

    /**
     * 参数类型是map集合
     */
    int insertStudentByMap(Map<String, Object> map);
}
