### **图分析系统设计与实现**
***

#### **功能模块**
***

* 数据源模块
* 权限控制模块
* 图展示模块
* 图分析模块

#### **添加功能模块方法**
***

* 怎么使用mvc设计模式进行开发，在源代码中增加了一个开发过程的demo，其中给出了一个mvc设计模式的实例

请看其中的以test(忽略大小写)开头的代码部分，代码的执行顺序为: **index.jsp**(/graphanalysis/web/index.jsp)-->**TestController.java**(/graphanalysis/src/com/uniplore/graph/ms/controller/TestController.java)-->**TestService.java**(/graphanalysis/src/com/uniplore/graph/ms/service/TestService.java)--> **TestUserDao.java**(/graphanalysis/src/com/uniplore/graph/ms/dao/impl/TestUserDao.java)-->**mapper**(mapper的设计请见TestMapper.java和TestMapper.xml)

* 根据上述步骤添加新的功能模块

#### **异常处理**
***

请注意在该系统的源代码中定义了自定义的异常处理器，所以在每一个方法中按照下面的格式写：

```java
return_type method_name(params...) throws Exception{
    ...
}
```