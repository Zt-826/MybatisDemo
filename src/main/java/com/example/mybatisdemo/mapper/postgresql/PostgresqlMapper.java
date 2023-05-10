package com.example.mybatisdemo.mapper.postgresql;

import com.example.mybatisdemo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostgresqlMapper {
    List<User> queryAllUsers();

    List<User> queryUser(@Param("user") User user);

    int insertUser(@Param("user") User user);

    int insertUsers(@Param("users") List<User> users);

    int updateUser(@Param("user") User user);

    int deleteAllUsers();

    int deleteUser(@Param("user") User user);
}
