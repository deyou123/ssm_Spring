package com.ssm.assemble;

import java.util.List;

/**
 * Created by 逸足天涯
 * on 11/7/2019.
 */
public class User {
    private String userName;
    private String password;
    private List<String> list;


    /*
    * 1.使用构造注入，提供带有参数的构造方法
    * */
    public User(String userName, String password, List<String> list) {
        this.userName = userName;
        this.password = password;
        this.list = list;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", list=" + list +
                '}';
    }
    /*
    * 2.使用设置注入
    * 2.1 提供默认空参构造方法
    * 2.2 为所有属性提供setter() 方法
    * */

    public User() {
        super();
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
