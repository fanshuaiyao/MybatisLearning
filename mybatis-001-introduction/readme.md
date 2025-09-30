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
  - 小技巧：凡是遇到resource这个单词  大概率是从类的根路径下开始加载（开始查找）
  -         InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml")
  - 通过系统类加载器来进行加载  mybatis的resources底层就是ClassLoader.getSystemClassLoader()

3. mybatis中的事务管理机制
- 在mybatis-config.xml的配置文件中可以继续进行事务管理
  - 提供了两种管理机制  <tarnsactionManager typr="JDBC"/> type的属性有两种：
  - JDBC事务管理器
  - MANAGED事务管理器
- jdbc；mybatis框架自己管理事务，采用jdbc原生的代码自动管理，
  - jdbc中如果没有执行 conn.setAutoCommit(false) 的话 默认是自动提交事务的
- managed：mybatis不在负责事务的管理了，让别人管，例如spring
- autoCommit=true  不能进行事务管理了

4. 日志配置
   - <setting name='logImpl' value = "STDOUT_LOGGING"></setting>
   - logback实现了slf4j标准（日志标准）
     - 引入依赖
     - 配置xml文件，必须叫logback.xml或者logback-test.xml
     - 位置必须放在类的跟路径下