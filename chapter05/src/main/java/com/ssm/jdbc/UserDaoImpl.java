package com.ssm.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by 逸足天涯
 * on 11/13/2019.
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    /*赠送积分*/
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,readOnly = false)
    public void transfer(String outUser, String inUser, int jf) {
        //接收jifen
        this.jdbcTemplate.update("update user set jf=jf+? where username=?",jf,inUser);
        //模拟运行中遇到的突发情况
       //   int i = 1/0;
        this.jdbcTemplate.update("update user set jf=jf-? where username=?",jf,outUser);
    }
}
