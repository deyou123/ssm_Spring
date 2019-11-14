# Spring 连接数据库

1. 在mysql 中创建一个名为db_spring 的数据库。

   ```mysql
   create database db_spring;
   use db_spring;
   ```

2. 配置pom.xml

```xml
 <!--Spring 核心包-->
    <dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.2.0.RELEASE</version>
    </dependency>
        
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.0.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.15</version>
        </dependency>
    </dependencies>
```

3. 在 applIcationContext.xml 配置数据库连接

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--配置数据源，这里使用mysql8.0.15 数据库-->
    <bean id="mysql" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <!--数据库驱动-->
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"></property>
        <!--数据rul-->
        <property name="url" value="jdbc:mysql://localhost:3306/db_spring?serverTimezone=UTC"></property>
        <!--数据库username-->
        <property name="username" value="root"></property>
        <!--数据库password-->
        <property name="password" value="666666"></property>
    </bean>
    <!--配置JDBC Template-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="mysql"></property>
    </bean>
</beans>
```

4. 测试类

```java
package com.ssm.jdbc;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 * Created by 逸足天涯
 * on 11/12/2019.
 */
/*使用执行方法创建表*/
public class JdbcTemplateTest {
    public static void main(String[] args) {
        //加载Spring配置文件。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取JdbcTemplate 实例
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        //使用执行方法执行sql 语句创建用户表User
        String sql = "create table user (" +
                "id int primary key auto_increment," +
                "username varchar (40)," +
                "password varchar (40))";
        jdbcTemplate.execute(sql);
    }
}

```

使用

```
show tables;
```

查看数据库user 表是否创建成功。

![1573648975686](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1573648975686.png)

# 接下来演示使用update() 方法添加用户

## 1. 新建User.java

```java
package com.ssm.jdbc;

/**
 * Created by 逸足天涯
 * on 11/13/2019.
 */
//User 实例
public class User {
    private Integer id;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

```

## 新建UserDao.java

```java
package com.ssm.jdbc;

import java.util.List;

/**
 * Created by 逸足天涯
 * on 11/13/2019.
 */
public interface UserDao {
    //添加用户方法
    public int addUser(User user);
 }

```

## 新建 UserDao 的实现类 UserDaoImpl.java

```java
package com.ssm.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;


/**
 * Created by 逸足天涯
 * on 11/13/2019.
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
   
    public int addUser(User user) {
        String sql ="insert into user(username,password) value(?,?)";
        Object [] obj=  new Object[]{
          user.getUserName(),
          user.getPassword()
        };
        int num= this.jdbcTemplate.update(sql,obj);
        return num;
    }
}

```

# 配置 UserDaoImpl 实例
```xml
<bean id="useDao" class="com.ssm.jdbc.UserDaoImpl">
        <property name="jdbcTemplate" ref="jdbcTemplate"></property>
    </bean>
```

## 测试
1. pom.xml 添加Junit 依赖

```xml
 <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
</dependency>
```

```java
/*添加用户*/
    @Test
    public void addUserTest(){
        //加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取UserDao实例
        UserDao userDao = (UserDao) applicationContext.getBean("useDao");
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("666666");
        int num = userDao.addUser(user);
        if(num > 0){
            System.out.println("成功插入了" + num + "条数据");

        }else {
            System.out.println("插入操作失败！");
        }
    }
```

其他的修改、删除、查询、通过ID查询用户、查询所有用户具体代码，看项目源文件。

