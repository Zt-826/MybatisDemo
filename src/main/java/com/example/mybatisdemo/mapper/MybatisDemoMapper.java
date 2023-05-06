package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MybatisDemoMapper {
    List<User> queryAllUsers();

    List<User> queryUser(@Param("user") User user);

    int insertUser(@Param("user") User user);

    int insertUsers(@Param("users") List<User> users);

    int updateUser(@Param("user") User user);

    int deleteAllUsers();

    int deleteUser(@Param("user") User user);

}
