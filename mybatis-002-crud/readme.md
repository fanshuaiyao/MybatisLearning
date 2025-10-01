## 使用mybatis完成crud

c:create
r:retrieve
u:update
d:delete

在JDBC中占位符是？ mybatis中是#{} 必须是这个
且#{}中应该填写的是  **属性的get方法的方法名称去掉get**  eg：#{carNum}  -> getCarNum()
严格意义来讲 #{}中 使用pojo类传递的话  大括号中应该写get方法去掉get然后将剩下的部分首字母小写 放进去

mybatis 看到#{carNum} 然后进行拼接 getCarNum 然后通过通过**反射机制**访问这个get方法  拿到对象的carNum属性 


查询后下划线和pojo的属性值不符合，复制失败：Car{id=19, carNum='null', brand='audi7', guidePrice=null, produceTime='null', carType='null'}
需要建立**别名**
*mybatis底层利用mysql的别名来进行字段和属性名称的映射*

本质是起别名 正好能映射上0000