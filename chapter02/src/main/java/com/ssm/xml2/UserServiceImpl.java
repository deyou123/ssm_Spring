package com.ssm.xml2;

/**
 * Created by 逸足天涯
 * on 11/8/2019.
 */
//使用Service 标识符将本类标识为bean

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save() {
        this.userDao.save();
        System.out.println("执行userService.save()");
    }
}
