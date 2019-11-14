package com.ssm.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 逸足天涯
 * on 11/14/2019.
 */
public class TranstationTest {
    @Test
    public void xmlTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        //赠送积分
        userDao.transfer("zhangsan","lisi",100);
        System.out.println("赠送积分成功!");

    }

    @Test
    public void annnotationTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/ssm/jdbc/applicationContext-annotation.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        //赠送积分
        userDao.transfer("zhangsan","lisi",100);
        System.out.println("赠送积分成功!");

    }
}
