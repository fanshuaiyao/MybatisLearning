package org.fan.core;

/**
 * 封装mapper文件的标签类，封装了一个sql标签
 *
 * @author fanshuaiyao
 * @version 1.0
 */
public class MappedStatement {
    /**
     * 结果集类型  只有select 这个属性才有值
     */
    private String resultType;

    /**
     * sql语句
     */
    private String sql;

    public MappedStatement(String resultType, String sql) {
        this.resultType = resultType;
        this.sql = sql;
    }

    public MappedStatement() {
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "resultType='" + resultType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
