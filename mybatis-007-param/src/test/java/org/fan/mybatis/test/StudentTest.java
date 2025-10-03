package org.fan.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.fan.mybatis.mapper.StudentMapper;
import org.fan.mybatis.pojo.Student;
import org.fan.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

public class StudentTest {
    /**
     * 当方法中的参数只有一个 且都是简单的参数
     */
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectById(1L);
        System.out.println(student);
        sqlSession.close();
    }

}
