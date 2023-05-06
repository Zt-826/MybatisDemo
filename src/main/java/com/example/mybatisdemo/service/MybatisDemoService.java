package com.example.mybatisdemo.service;

import com.example.mybatisdemo.bean.User;
import com.example.mybatisdemo.mapper.MybatisDemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class MybatisDemoService {
    @Resource
    private MybatisDemoMapper mapper;

    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    public List<User> queryAllUsers() {
        return mapper.queryAllUsers();
    }

    /**
     * 根据条件查询用户
     *
     * @param user user
     * @return List<User>
     */
    public List<User> queryUser(User user) {
        return mapper.queryUser(user);
    }

    /**
     * 插入用户
     *
     * @param user user
     * @return int
     */
    public int insertUser(User user) {
        return mapper.insertUser(user);
    }

    /**
     * 批量插入用户
     *
     * @param users users
     * @return int
     */
    public int insertUsers(List<User> users) {
        return mapper.insertUsers(users);
    }

    /**
     * 修改用户
     *
     * @param user user
     * @return int
     */
    public int updateUser(User user) {
        return mapper.updateUser(user);
    }

    /**
     * 根据条件删除用户
     *
     * @param user user
     * @return int
     */
    public int deleteUser(User user) {
        return mapper.deleteUser(user);
    }

    /**
     * 删除所有用户
     *
     * @return int
     */
    public int deleteAllUsers() {
        return mapper.deleteAllUsers();
    }
}
