# Spring基础

# 工具

IDEA

Maven

Spring 4.3.6

## 1.创建一个简单的Maven 工程

```xml
 <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.3.6.RELEASE</version>
        </dependency>
```

## 2. 在resources 文件下新建applicationContex.xml 文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```

#  3. 新建一个UserDao 接口和UserDaoImpl

```java
package com.ssm.ioc_di;

/**
 * Created by 逸足天涯
 * on 11/7/2019.
 */
public interface UserDao {
    public void login();
}

```

```java
package com.ssm.ioc_di;

/**
 * Created by 逸足天涯
 * on 11/7/2019.
 */
public class UserDaoImpl implements UserDao {
    //实现用户登录方法
    public void login() {
        System.out.println("用户登录");
    }
}

```

# 4. 创建IOC测试类

在applicationContext 中添加userDao实例配置

```xml
<!--使用Spring 创建对象实例-->
    <bean id="userDao" class="com.ssm.ioc_di.UserDaoImpl"></bean>
```

测试：

```java
package com.ssm.ioc_di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 逸足天涯
 * on 11/7/2019.
 */
public class Ioc {
    public static void main(String[] args) throws Exception {
        //初始化Spring容器，加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //通过容器获取实例对象
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        //调用实例中的login方法
        userDao.login();
    }
}

```

运行结果

![1573208827286](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1573208827286.png)

# 5. 新建UserService接口 和UserServiceImpl类

```java
package com.ssm.ioc_di;

/**
 * Created by 逸足天涯
 * on 11/7/2019.
 */
public interface UserService {
    public void login();
}

```

```java
package com.ssm.ioc_di;

/**
 * Created by 逸足天涯
 * on 11/7/2019.
 */
public class UserServiceImpl implements UserService{
    //声明UseDao 属性
    private UserDao userDao;

    //添加 userDao 属性的setter 方法，用于实现依赖注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    //实现接口的方法
    public void login() {
        //调用属性的方法。并执行输出语句
        userDao.login();
        System.out.println("UserService login()");
    }
}

```

spring 容器中配置userService的Bean,将name=userDao 的实例注入到UserServiceImple中

```xml
<bean id="userService" class="com.ssm.ioc_di.UserServiceImpl">
        <!--将name 为userDao实例注入到userService实例中去-->
        <property name="userDao" ref="userDao"></property>
    </bean>
```

# 6. DI 测试

```java
package com.ssm.ioc_di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 逸足天涯
 * on 11/7/2019.
 */
public class DI {
    public static void main(String[] args) {
        //初始化Spring容器，加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过容器获取userService实例对象
        UserService userService = (UserService) applicationContext.getBean("userService");
        //调用实例中的login方法
        userService.login();

    }
}

```

运行结果：

![1573216172270](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1573216172270.png)

思考：

什么是Spring 的Ioc和DI?