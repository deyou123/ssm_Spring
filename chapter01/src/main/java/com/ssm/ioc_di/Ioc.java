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
