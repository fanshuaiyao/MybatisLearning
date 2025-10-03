package org.fan.mybatis.test;

import org.apache.ibatis.session.SqlSession;
import org.fan.mybatis.mapper.StudentMapper;
import org.fan.mybatis.pojo.Student;
import org.fan.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    @Test
    public void testInsertByMap(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "小王");
        map.put("age", 18);
        map.put("height", 1.78);
        map.put("birth", new Date());
        map.put("sex", '男');
        int i = mapper.insertStudentByMap(map);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectStudentByNameAndSex(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectStudentByNameAndSex("小王", '男');
        students.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void testSelectStudentByNameAndSex2(){
        SqlSession sqlSession = SqlSessionUtil.openSqlsession();
        // 代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // 所以这也是代理方法
        List<Student> students = mapper.selectStudentByNameAndSex2("小王", '男');
        students.forEach(System.out::println);
        sqlSession.close();
    }


}
