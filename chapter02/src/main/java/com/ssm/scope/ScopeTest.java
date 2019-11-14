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
        /*针对同一个类有且仅有一个id 的情况可以使用*/
        Scope bean = applicationContext.getBean(Scope.class);
        System.out.println(bean);
        //输出获取的实例
        System.out.println(applicationContext.getBean("scope"));
        System.out.println(applicationContext.getBean("scope"));
    }
}
