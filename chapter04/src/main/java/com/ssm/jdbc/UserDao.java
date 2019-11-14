package com.ssm.jdbc;

import java.util.List;

/**
 * Created by 逸足天涯
 * on 11/13/2019.
 */
public interface UserDao {
    //添加用户方法
    public int addUser(User user);
    //更新用户
    public int updateUser(User user);

    //通过ID删除用户
    public int deleteUser(int id);
    //通过ID查询用户
    public User findUserById(int id);
    //查询所有用户
    public List<User>  findAllUser();
}
