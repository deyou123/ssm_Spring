package com.ssm.jdbc;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


/**
 * Created by 逸足天涯
 * on 11/12/2019.
 */

public class JdbcTemplateTest {
    /*创建用户表测试*/
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
    /*添加用户*/
    @Test
    public void addUserTest(){
        //加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取UserDao实例
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
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
/*更新用户*/
    @Test
    public void updateUserTest(){
        //加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取UserDao实例
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        User user = new User();
        user.setId(1);
        user.setUsername("tom");
        user.setPassword("666666");
        //更新用户
        int num = userDao.addUser(user);
        if(num > 0){
            System.out.println("成功更新了" + num + "条数据");

        }else {
            System.out.println("插入操作失败！");
        }
    }
    /*删除用户*/
    @Test
    public void deleteUserTest(){
        //加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取UserDao实例
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");

        int num = userDao.deleteUser(1);
        if(num > 0){
            System.out.println("成功删除了" + num + "条数据");

        }else {
            System.out.println("插入操作失败！");
        }
    }
    /*通过id 查询用户*/
    @Test
    public void findUserByIdTest(){
        //加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取UserDao实例
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        User user = userDao.findUserById(2);
        System.out.println(user);

    }
    /*查询所有用户*/
    @Test
    public void findAllUser(){
        //加载配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取UserDao实例
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        List<User> users = userDao.findAllUser();
        for (User user: users) {
            System.out.println(user);
        }
    }
}
