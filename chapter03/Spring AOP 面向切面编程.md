# Spring AOP 面向切面编程
目前流行的AOP框架有2个，一个是Spring AOP 一个是AspectJ,Spring
AOP是纯java实现，不需要专门的编译过程，和类加载器。运行过程中，同过代理方式向目标类中植入增强型代码，AspectJ
基于Java语言的AOP框架，从Spring 2.0 开始 Spring 引入了对AspectJ
的支持，AspectJ
扩展了Java语言，提供了一个专门的类加载器，在编译时提供横向的代码植入。

* Aspect(切面)，封装用于是横向插入系统功能（如事务，日志等）的类，该类主要被Spring容器识别为切面，需要在配置文件中通过<bean>元素的指定。 
*  Joinpoint(连接点)，程序执行过程中某个阶段点。实际上是对象的一个操作，在Spring Aop 中方法的调用。‘
*  pointCut(切点)，指的是切面与程序交流的交叉点，即需要处理的连接点
*  Advice (通知增强处理)AOP 在特定的切点执行增强处理，即将定义好的切入点处所要执行的程序代码，可以理解为切面类的方法，他是切面的具体实现。 
*  Target Object(目标对象) 指所用被通知的对象，也称为被增强对象，如果AOP框架采用的是动态AOP实现，那么该对象就是一个被代理对象。
*  Proxy(代理)将通知应用到目标对象，被动创建对象。
*  Weaving(植入) 将切面代码插入目标对象，从而生成代理对象的过程。

# pom.xml 

```xml
 <dependencies>
     
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.3.6.RELEASE</version>
        </dependency>
     
        <!-- 使用AspectJ 实现AOP  -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.4</version>
        </dependency>

    </dependencies>

    <build>
			<!--解决eclipse 向Idea 迁移找不到配置文件问题-->
            <resources>
                <resource>
                    <directory>${basedir}/src/main/java</directory>
                    <includes>
                        <include>**/*.properties</include>
                        <include>**/*.xml</include>
                    </includes>
                </resource>
                <resource>
                    <directory>${basedir}/src/main/resources</directory>
                </resource>
            </resources>
    </build>
```



# 基于 XML 声明式AspectJ                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

1. 定义一个目标类

```java 
package com.ssm.aspectj;

/**
 * Created by 逸足天涯
 * on 11/11/2019.
 */
public interface UserDao {
    public void save();
    public void delete();
}

```

2. 定义UserDao.java 的实现类

```java
package com.ssm.aspectj;

import org.springframework.stereotype.Repository;

/**
 * Created by 逸足天涯
 * on 11/11/2019.
 */

public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("添加用户");
//        System.out.println("添加错误代码：");
//        int i = 100/0;
    }

    public void delete() {
        System.out.println("删除用户");

    }
}

```



3. 定义一个切面

```
package com.ssm.aspectj.xml;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by 逸足天涯
 * on 11/11/2019.
 */
public class MyAspect {
    //前置通知
    public void myBefore(JoinPoint joinPoint){
        System.out.println("前置通知：模拟执行权限检查");
        System.out.println("目标类是：" + joinPoint.getTarget());
        System.out.println("被植入增强处理的目标方法为：" + joinPoint.getSignature().getName());
    }
    //后置通知
    public void myAfterReturnng(JoinPoint joinPoint){
        System.out.println("后置通知：模拟日志记录");
        System.out.println("被植入增强处理的目标方法！"+ joinPoint.getSignature().getName());
    }

    /*
    * 环绕通知
    * Proceeding joinPoint 是JoinPoint 的子接口，表示可执行目标方法
    * 1.必须Object类返回值
    * 2. 必须接受一个参数
    * 3.必须是Throws Trowabale
    * */
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        System.out.println("环绕开始，执行目标方法开始之前，模拟开启事务");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("环绕结束，执行目标方法开始之后，模拟关闭事务");
        return  obj;

    }

    //异常通知
    public void myAfterThrowing(JoinPoint joinPoint, Throwable e){
        System.out.println("异常通知：" + e.getMessage());
    }

    //最终通知
    public void myAfter(){
        System.out.println("模拟方法结束后，释放资源");
    }


}

```

4. 配置resources/applicationContext.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
<!--1. 目标类-->
    <bean id ="userDao" class="com.ssm.aspectj.UserDaoImpl"></bean>
    <!--2.切面-->
    <bean id="myAspect" class="com.ssm.aspectj.xml.MyAspect"></bean>
    <!--3.Aop编程-->

        <aop:config>
            <!--1.配置切面-->

            <aop:aspect id="aspect" ref="myAspect">
                <!--2.配置切入点-->
                <aop:pointcut id="myPointCut" expression="execution(* com.ssm.aspectj.*.*(..))"></aop:pointcut>
                <!--前置通知-->
                    <aop:before method="myBefore" pointcut-ref="myPointCut"></aop:before>

                    <!--后置通知-->
                    <aop:after method="myAfter" pointcut-ref="myPointCut"></aop:after>
                    <!--环绕通知-->
                    <aop:after-returning method="myAfterReturnng" pointcut-ref="myPointCut"></aop:after-returning>
                <!--异常通知-->
                    <aop:after-throwing method="myAfterThrowing" pointcut-ref="myPointCut" throwing="e"></aop:after-throwing>
                    <!--环绕通知-->
                    <aop:around method="myAround" pointcut-ref="myPointCut"></aop:around>
            </aop:aspect>


        </aop:config>
</beans>
```

5.测试

```java
package com.ssm.aspectj.xml;

import com.ssm.aspectj.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 逸足天涯
 * on 11/11/2019.
 */
public class TestXmlAspectJ {
    public static void main(String[] args) {
        //定义配置文件路径
        //初始化Spring容器，获取配置文件
       ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取UserDao 实例
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.save();
        //执行添加方法
    }
}

```



## 基于annotation 声明式的AspectJ 

1. 在目标类上添加UserDaoImple.java中添加

   @Repository("userDao")

2. 复制切面类MyAspect.java，并修改

```java
package com.ssm.aspectj.annotation;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by 逸足天涯
 * on 11/11/2019.
 */
/*切面类*/
@Aspect
@Component
public class MyAspect {


    /*定义切入点表达式*/
    @Pointcut("execution(* com.ssm.aspectj.*.*(..))")
    /*使用一个返回值为void,方法体为空的方法来命名切入点*/
    public void myPointCut(){}
    //前置通知
    @Before("myPointCut()")
    public void myBefore(JoinPoint joinPoint){
        System.out.println("前置通知：模拟执行权限检查");
        System.out.println("目标类是：" + joinPoint.getTarget());
        System.out.println("被植入增强处理的目标方法为：" + joinPoint.getSignature().getName());
    }
    //后置通知
    @AfterReturning(value = "myPointCut()")
    public void myAfterReturnng(JoinPoint joinPoint){
        System.out.println("后置通知：模拟日志记录");
        System.out.println("被植入增强处理的目标方法！"+ joinPoint.getSignature().getName());
    }

    /*
     * 环绕通知
     * Proceeding joinPoint 是JoinPoint 的子接口，表示可执行目标方法
     * 1.必须Object类返回值
     * 2. 必须接受一个参数
     * 3.必须是Throws Trowabale
     * */
    @Around(value = "myPointCut()")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        System.out.println("环绕开始，执行目标方法开始之前，模拟开启事务");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("环绕结束，执行目标方法开始之后，模拟关闭事务");
        return  obj;

    }

    //异常通知
    @AfterThrowing(value = "myPointCut()",throwing = "e")
    public void myAfterThrowing(JoinPoint joinPoint, Throwable e){
        System.out.println("异常通知：" + e.getMessage());
    }

    //最终通知
    @After(value = "myPointCut()")
    public void myAfter(){
        System.out.println("模拟方法结束后，释放资源");
    }


}


```

3. 改变applicationContext.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
       <!--指定需要扫描的包，使注解生效-->
       <context:component-scan base-package="com.ssm.aspectj"></context:component-scan>
       <!--启动基于注解的AspectJ支持-->
       <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
   </beans>
   ```

   

3.  添加测试类

```java
package com.ssm.aspectj.annotation;

import com.ssm.aspectj.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 逸足天涯
 * on 11/12/2019.
 */
public class TestAnnotation {
    public static void main(String[] args) {
       ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/ssm/aspectj/annotation/applicationContext.xml");
        UserDao userDao = (UserDao)applicationContext.getBean("userDao");
        userDao.save();
    }
}

```



   

