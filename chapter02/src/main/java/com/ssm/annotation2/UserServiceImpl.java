package com.ssm.annotation2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 逸足天涯
 * on 11/8/2019.
 */
//使用Service 标识符将本类标识为bean
@Service("userService")
public class UserServiceImpl implements UserService{
    //使用Resourece 注解注入
    @Resource(name = "userDao")
    private UserDao userDao;

    public void save() {
        this.userDao.save();
        System.out.println("执行userService.save()");
    }
}
