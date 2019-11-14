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
