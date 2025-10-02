package com.fan.bank.utils;

// mybatis 对javassist继续了二次包装，因为mybatis是轻量级的  只引入一个jar包
import org.apache.ibatis.javassist.CannotCompileException;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 可以动态生成DAO代理类
 * @author fanshuaiyao、
 * @version 1.0
 */
public class GenerateDaoProxy {

    /**
     * 生成dao接口的实现类  并且将实现类的对象创建出来
     * @param daoInterface
     * @return
     */
    public static Object generate(SqlSession sqlSession, Class daoInterface){

        // 类池
        ClassPool pool = ClassPool.getDefault();

        // 制造类
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Proxy");

        // 制造接口
        CtClass ctClassInterface = pool.makeInterface(daoInterface.getName());

        // 实现接口
        ctClass.addInterface(ctClassInterface);

        Method[] declaredMethods = daoInterface.getDeclaredMethods();
        Arrays.stream(declaredMethods).forEach(method -> {
            try {

                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");
                methodCode.append(method.getReturnType().getName());
                methodCode.append(" ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 需要方法的形式参数列表
                Class<?>[] parameterTypes = method.getParameterTypes();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> parameterType = parameterTypes[i];
                    methodCode.append(parameterType.getName());
                    methodCode.append(" ");
                    methodCode.append("arg").append(i);
                    if (i != parameterTypes.length - 1) {
                        methodCode.append(", ");
                    }
                }
                methodCode.append(")");
                methodCode.append("{");
                methodCode.append("org.apache.ibatis.session.SqlSession sqlSession = com.fan.bank.utils.SqlSessionUtil.openSession();");
                // 需要知道是什么类型的sql语句
                // sql语句的id是框架使用者提供的，具有多变性。对于我框架的开发人员来说。我不知道。
                // 既然我框架开发者不知道sqlId，怎么办呢？mybatis框架的开发者于是就出台了一个规定：凡是使用GenerateDaoProxy机制的。
                // sqlId都不能随便写。namespace必须是dao接口的全限定名称。id必须是dao接口中方法名。
                String sqlId = daoInterface.getName() + "." + method.getName();
                SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
                if (sqlCommandType == SqlCommandType.INSERT) {

                }
                if (sqlCommandType == SqlCommandType.DELETE) {

                }
                if (sqlCommandType == SqlCommandType.UPDATE) {
                    methodCode.append("return sqlSession.update(\"" + sqlId + "\", arg0);");
                }
                if (sqlCommandType == SqlCommandType.SELECT) {
                    methodCode.append("return sqlSession.selectOne(\"" + sqlId + "\", arg0);");
                }

                methodCode.append("}");
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Object obj = null;
        try {
            Class<?> clazz = ctClass.toClass();
            obj = clazz.newInstance();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return obj;
    }
}
