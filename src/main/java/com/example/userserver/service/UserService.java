package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.User;


public interface UserService {



	public Object getAllUser(PageParam<User> pageParam);

    public boolean removeUserById(int id);

    public Object addUser(User user);

    public boolean updateUser(User user);

    public User getUserById(int id);




}