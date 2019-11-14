package com.ssm.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
    //更新用户方法

    public int updateUser(User user) {
        String sql = "insert into user(username,password) value(?,?)";
        Object [] obj = new Object[]{

                user.getUserName(),
                user.getPassword(),
                user.getId(),
        };
        int num = this.jdbcTemplate.update(sql,obj);
        return num;
    }
/*添加用户*/
    public int addUser(User user) {
        String sql ="insert into user(username,password) value(?,?)";
        Object [] obj=  new Object[]{
          user.getUserName(),
          user.getPassword()
        };
        int num= this.jdbcTemplate.update(sql,obj);
        return num;
    }
    /*删除用户*/
    public int deleteUser(int id) {
        String sql = "delete from user where id = ?";
        int num= this.jdbcTemplate.update(sql,id);
        return num;
    }



    //查询用户
    public User findUserById(int id) {
        String sql = "select * from user where id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);

        return this.jdbcTemplate.queryForObject(sql,rowMapper,id);
    }
    //查询所有用户
    public List<User> findAllUser() {
        String  sql = "select * from user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        return this.jdbcTemplate.query(sql,rowMapper);
    }

}
