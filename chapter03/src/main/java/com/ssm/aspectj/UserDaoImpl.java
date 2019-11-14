package com.ssm.aspectj;

import org.springframework.stereotype.Repository;

/**
 * Created by 逸足天涯
 * on 11/11/2019.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("添加用户");
//        System.out.println("添加错误代码：");
//        int i = 100/0;
    }

    public void delete() {
        System.out.println("删除用户");

    }
}
