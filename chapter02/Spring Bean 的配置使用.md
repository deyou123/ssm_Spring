# 关于读取配置属性文件的路径问题Spring Bean 的配置使用

## 1.IDEA 新建一个Maven 工程
引入依赖
```xml
  <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.3.6.RELEASE</version>
        </dependency>

```

# 2. 配置applicationContext.xml文件
在 resources 文件夹下新建一个.xml的  spring 配置 文件。配置好如下

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
<bean id="scope" class="com.ssm.scope.Scope" scope="singleton"></bean>
  <!--  <bean id="scope" class="com.ssm.scope.Scope" scope="prototype"></bean>-->
</beans>
```



## 2. Singleton 和 Prototy 的作用域

新建一个Scope.java 类



```java
package com.ssm.scope;

/**
 * Created by 逸足天涯
 * on 11/8/2019.
 */
public class Scope {
}

```

# 3.新建一个ScopeTest类

```java
package com.ssm.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 逸足天涯
 * on 11/8/2019.
 */
public class ScopeTest {
    public static void main(String[] args) {
        //初始化Spring 容器，加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //输出获取的实例
        System.out.println(applicationContext.getBean("scope"));
        System.out.println(applicationContext.getBean("sco
                                                      pe"));
    }
}

```

applicationContext.xml 中。使用

```xml
  <!--scope 默认为singleton-->
    <bean id="scope" class="com.ssm.scope.Scope" scope="singleton"></bean>
```

运行ScopeTest 主方法

![1573217282881](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1573217282881.png)

修改为

```xml
<!--  <bean id="scope" class="com.ssm.scope.Scope" scope="prototype"></bean>-->
```

运行ScopeTest 主方法

![1573217378600](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1573217378600.png)

