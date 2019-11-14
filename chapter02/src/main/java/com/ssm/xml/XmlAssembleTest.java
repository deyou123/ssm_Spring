package com.ssm.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by 逸足天涯
 * on 11/8/2019.
 */
public class XmlAssembleTest {
private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        //定义配置文件路径
         applicationContext = new ClassPathXmlApplicationContext("com/ssm/xml/bean1.xml");
        //获取UserController实例
        //调用UserContriller实例的save()方法
        UserController userController = (UserController)applicationContext.getBean("userController");
        userController.save();

    }

}
