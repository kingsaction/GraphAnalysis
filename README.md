### **图分析系统设计与实现**
***

#### **功能模块**
***

* 用户管理模块
* 数据源模块
* 图展示模块
* 图分析模块

#### **异常处理**
***

请注意在该系统的源代码中定义了自定义的异常处理器，所以在每一个方法中按照下面的格式写(**一定要在每一个方法上写throws Exception**)：

```java
public/private/protected return_type method_name(params...) throws Exception{
    ...
}
```