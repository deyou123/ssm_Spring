package com.ssm.annotation3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by 逸足天涯
 * on 11/8/2019.
 */
@Controller("userController")
public class UserController {
    @Resource(name="userService")
   // @Autowired
    private UserService userService;

    public void save(){
        this.userService.save();
        System.out.println("执行UserController.save()");
    }
}
