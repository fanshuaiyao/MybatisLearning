package org.fan.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.fan.mybatis.pojo.Student;

import java.util.List;
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

    /**
     * 如果是多个参数 mybatis是底层自己创建一个map集合  然后put进去  以这种方式存储
     * map.put(arg0, name);
     * map.put(arg1, sex);
     * map.put(param1, name);
     * map.put(param2, sex);   param是从1开始  arg和param都放在了一个map中  能混着用
     * @param name
     * @param sex
     * @return
     */
    List<Student> selectStudentByNameAndSex(String name, Character sex);


    /**
     * param注解  起别名  要不然mybatis起的名字就是arg0  用了param就把别名放到map中的key 中
     *      * map.put("name", name);
     *      * map.put("sex", sex);
     *  但是param1 2的方式还在  但是arg0 1的方式没有了
     * @param name
     * @param sex
     * @return
     */
    List<Student> selectStudentByNameAndSex2(@Param("name") String name, @Param("sex") Character sex);
}
