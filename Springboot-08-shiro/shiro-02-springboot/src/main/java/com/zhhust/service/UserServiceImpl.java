package com.zhhust.service;

import com.zhhust.mapper.UserMapper;
import com.zhhust.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public List<User> queryAllUsers() {
        List<User> users = userMapper.queryAllUsers();
        return users;
    }

    @Override
    public User queryUserByName(String name) {
        User user = userMapper.queryUserByName(name);
        return user;
    }
}
