package com.ssm.ioc_di;

/**
 * Created by 逸足天涯
 * on 11/7/2019.
 */
public class UserServiceImpl implements UserService{
    //声明UseDao 属性
    private UserDao userDao;

    //添加 userDao 属性的setter 方法，用于实现依赖注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    //实现接口的方法
    public void login() {
        //调用属性的方法。并执行输出语句
        userDao.login();
        System.out.println("UserService login()");
    }
}
