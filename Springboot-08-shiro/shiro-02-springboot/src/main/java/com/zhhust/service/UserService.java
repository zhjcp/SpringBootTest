package com.zhhust.service;

import com.zhhust.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> queryAllUsers();
    public User queryUserByName(String name);
}
