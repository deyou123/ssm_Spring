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
