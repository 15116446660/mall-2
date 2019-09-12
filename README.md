1、【参考】分层领域模型规约：   
• DO（Data Object）：此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。    
• DTO（Data Transfer Object）：数据传输对象，Service 或 Manager 向外传输的对象。   
• VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。
注：阿里巴巴开发规范——第38页

2、【参考】各层命名规约：  
A) Service/DAO 层方法命名规约  
1） 获取单个对象的方法用 get 做前缀。  
2） 获取多个对象的方法用 list 做前缀，复数形式结尾如：listObjects。  
3） 获取统计值的方法用 count 做前缀。  
4） 插入的方法用 save/insert 做前缀。    
5） 删除的方法用 remove/delete 做前缀。   
6） 修改的方法用 update 做前缀。  
B) 领域模型命名规约  
1） 数据对象：xxxDO，xxx 即为数据表名。  
2） 数据传输对象：xxxDTO，xxx 为业务领域相关的名称。  
3） 展示对象：xxxVO，xxx 一般为网页名称。  
4） POJO 是 DO/DTO/BO/VO 的统称，禁止命名成 xxxPOJO。  
注：阿里巴巴开发规范——第3页

3、数据库建表规约
注：参考阿里巴巴开发规范第31页

4、全局统一业务异常类：BusinessException

5、平台端、商家端统一返回code：0：成功  1：失败
   移动端设置维护枚举类code：0成功  其他：失败
   
6、Controller 只返回成功，其余所有失败都抛出异常，由全局异常类统一捕获并返回失败结果。