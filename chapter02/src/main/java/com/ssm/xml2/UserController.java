package com.ssm.xml2;

/**
 * Created by 逸足天涯
 * on 11/8/2019.
 */

public class UserController {


    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void save(){
        this.userService.save();
        System.out.println("执行UserController.save()");
    }


}
