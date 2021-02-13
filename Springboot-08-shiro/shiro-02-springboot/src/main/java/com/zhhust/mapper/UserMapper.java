package com.zhhust.mapper;

import com.zhhust.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    public List<User> queryAllUsers();

    public User queryUserByName(String name);
}
