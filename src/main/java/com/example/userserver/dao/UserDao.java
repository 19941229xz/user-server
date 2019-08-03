package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.User;

import java.util.List;

public interface UserDao {


    List<User> getAllUser(User user);

    @Delete("delete from user where id = #{id}")
    int removeUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    @Select("select * from user where id =#{id}")
    User getUserById(int id);

    @Select("select * from user where userName =#{userName}")
    User getUserByUserName(String userName);




}