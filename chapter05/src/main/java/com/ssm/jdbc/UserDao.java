package com.ssm.jdbc;

import com.sun.imageio.plugins.common.I18N;

import java.util.List;

/**
 * Created by 逸足天涯
 * on 11/13/2019.
 */
public interface UserDao {

    public void transfer(String outUser, String inUser, int jf);

}
