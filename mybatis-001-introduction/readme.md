开发我的第一个mybatis

1. resources文件夹下的文件相当于放在了类目录下
2. 打包方式：jar

## 开发步骤
- 依赖 mybatis mysql驱动
- 编写核心配置文件   mybatis-config  不一定叫这个名字  不一定放在类路径下
- 通过mybatis config文件 创建sqlSessionFactory   一个很重要的类 
- 有两个很重要的xml文件  一个是mybatis-config.xml（一个）   一个是XxxxMapper.xml文件（一个表一个） 
- 在mapper文件中编写sql语句
- 在核心配置文件中  关联到mapper文件路径
- 编写mybatis程序 使用mybatis的类库
  - 在mybatis中执行sql语句的是 sqlSession对象  是java程序和数据库的一次对话   这个对象是sqlSessionFactory产生的（工厂产生）
  - 获取sqlSessionFactoryBuilder对象  通过它获取sqlSessionFactory   再创建sqlSession
  - sqlSessionFactoryBuilder  -> sqlSessionFactory -> sqlSession
    