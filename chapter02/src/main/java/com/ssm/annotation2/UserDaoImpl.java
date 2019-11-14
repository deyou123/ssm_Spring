package com.ssm.annotation2;

import org.springframework.stereotype.Repository;

/**
 * Created by 逸足天涯
 * on 11/8/2019.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("userDao.save");
    }
}
