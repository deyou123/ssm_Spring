package com.ssm.assemble;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 逸足天涯
 * on 11/8/2019.
 */
public class XmlAssembleTest {
    public static void main(String[] args) {
        //初始化Spring 容器，加载配置文件
       ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = (User) applicationContext.getBean("user1");

        System.out.println(applicationContext.getBean("user1"));
        System.out.println(applicationContext.getBean("user2"));
        /*针对同一类有且仅有一个id 的情况可以使用*/
        User u = applicationContext.getBean(User.class);
        System.out.println(u);
    }
}
