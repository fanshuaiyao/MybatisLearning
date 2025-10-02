package org.fan.core.core;

import org.fan.core.statement.MappedStatement;

import java.lang.reflect.Method;
import java.sql.*;

/**
 * 专门负责sql语句执行的会话对象
 *
 * @author fanshuaiyao
 * @version 1.0
 */
public class SqlSession {
    private SqlSessionFactory sqlSessionFactory;

    public SqlSession(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 像数据库插入对象
     *
     * @param sqlId sql语句id
     * @param pojo  插入的数据
     * @return 影响的条数
     */
    public int insert(String sqlId, Object pojo) {

        int count = 0;
        try { // JDBC代码
            Connection connection = sqlSessionFactory.getTransaction().getConnection();
            String godbatisSql = sqlSessionFactory.getMappedStatements().get(sqlId).getSql();
            // 解析一下 换成？
            String sql = godbatisSql.replaceAll("#\\{[a-zA-Z0-9_$]*}", "?");
            // 编译sql语句
            PreparedStatement ps = connection.prepareStatement(sql);
            // 给？占位符传值  给第几个？传什么值
            int fromIndex = 0;
            int index = 1;
            while (true) {
                int jingIndex = godbatisSql.indexOf("#", fromIndex);
                if (jingIndex < 0) {
                    break;
                }
                int youKuoHaoIndex = godbatisSql.indexOf("}", fromIndex);
                String propertyName = godbatisSql.substring(jingIndex + 2, youKuoHaoIndex).trim();
                fromIndex = youKuoHaoIndex + 1;
                // 有属性名：id 怎么获取id的属性值？ 调用get方法
                // 获取get方法名  -> getId
                String getMethodName = "get" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                // 通过反射获取get方法  -> getId()
                Method getMethod = pojo.getClass().getDeclaredMethod(getMethodName);
                // 执行get方法获取属性的值  -> id=1003
                Object propertyValue = getMethod.invoke(pojo);
                ps.setString(index, propertyValue.toString());
                index++;
            }

            // 执行sql
            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * selectOne
     */
    public Object selectOne(String sqlId, Object param) {

        Object obj = null;
        try {
            Connection connection = sqlSessionFactory.getTransaction().getConnection();
            MappedStatement mappedStatement = sqlSessionFactory.getMappedStatements().get(sqlId);
            String godbatisSql = mappedStatement.getSql();
            String sql = godbatisSql.replaceAll("#\\{[a-zA-Z0-9_$]*}", "?");
            // 编译sql语句
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, param.toString());

            ResultSet rs = ps.executeQuery();
            String resultType = mappedStatement.getResultType();
            if (rs.next()) {
                Class<?> resultTypeClass = Class.forName(resultType);
                obj = resultTypeClass.newInstance();
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String propertyName = rsmd.getColumnName(i+1);
                    String setMethodName = "set" + propertyName.toUpperCase().charAt(0) + propertyName.substring(1);
                    Method setMethod = resultTypeClass.getDeclaredMethod(setMethodName, String.class);
                    setMethod.invoke(obj, rs.getString(propertyName));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }


    public void commit() {
        sqlSessionFactory.getTransaction().commit();
    }


    public void rollback() {
        sqlSessionFactory.getTransaction().rollback();
    }

    public void close() {
        sqlSessionFactory.getTransaction().close();
    }


}
