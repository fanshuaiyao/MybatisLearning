package test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class ConfigurationTest {

    @Test
    public void testOutTime() throws Exception{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 指定环境获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsReader("mybatis-config.xml"), "development");
        for (int i = 0; i < 4; i++) {
            SqlSession sqlSession1 = sqlSessionFactory.openSession();
            int insertCar = sqlSession1.insert("insertCar");
        }
    }
    @Test
    public void testDataSource() throws Exception{
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 指定环境获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsReader("mybatis-config.xml"), "development");

        // 会话一
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        int insertCar = sqlSession1.insert("insertCar");
        sqlSession1.commit();
        sqlSession1.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        int insertCar1 = sqlSession2.insert("insertCar");
        sqlSession2.commit();
        sqlSession2.close();

    }

    @Test
    public void testEnv() throws Exception {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 指定环境获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsReader("mybatis-config,xml"), "default");

    }
}
