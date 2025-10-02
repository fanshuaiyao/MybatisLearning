package org.fan.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * mybatis工具类
 * @author fanshuaiyao
 * @version 1.0
 */
public class SqlSessionUtil {

    private static final SqlSessionFactory sqlSessionFactory;

    private SqlSessionUtil(){}

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static final ThreadLocal<SqlSession> local = new ThreadLocal<>();

    /**
     * 获取会话对象
     * @return 会话对象
     */
    public static SqlSession openSqlsession(){
        SqlSession sqlSession = local.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            // 将sqlSession对象绑定到当前的对象
            local.set(sqlSession);
        }
        return sqlSession;
    }

    /**
     * 关闭绑定
     * @param sqlSession
     */
    public static void close(SqlSession sqlSession){
        if (sqlSession != null) {
            sqlSession.close();
            // 移除绑定关系
            // 因为tomcat服务支持线程池  也就是说 用过的线程对象t1  可能下一次还会被用到
            local.remove();
        }
    }
}
