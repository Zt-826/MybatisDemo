package com.example.mybatisdemo.service;

import com.example.mybatisdemo.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
//@Transactional(rollbackFor = Exception.class, value = "transactionalManager")
public class DynamicDataSourceService {

    @Resource
    private MysqlService mysqlService;

    @Resource
    private PostgresqlService postgresqlService;

    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    public List<User> queryAllUsers() {
        List<User> users = mysqlService.queryAllUsers();
        users.addAll(postgresqlService.queryAllUsers());
        return users;
    }

    /**
     * 根据条件查询用户
     *
     * @param user user
     * @return List<User>
     */
    public List<User> queryUser(User user) {
        List<User> users = mysqlService.queryUser(user);
        users.addAll(postgresqlService.queryUser(user));
        return users;
    }

    /**
     * 插入用户
     *
     * @param user user
     * @return int
     */
    public int insertUser(User user) {
        int result = mysqlService.insertUser(user);
        result += postgresqlService.insertUser(user);
        return result;
    }

    /**
     * 批量插入用户
     *
     * @param users users
     * @return int
     */
    public int insertUsers(List<User> users) {
        int result = mysqlService.insertUsers(users);
        result += postgresqlService.insertUsers(users);
        return result;
    }

    /**
     * 修改用户
     *
     * @param user user
     * @return int
     */
    public int updateUser(User user) {
        int result = mysqlService.updateUser(user);
        result += postgresqlService.updateUser(user);
        return result;
    }

    /**
     * 根据条件删除用户
     *
     * @param user user
     * @return int
     */
    public int deleteUser(User user) {
        int result = mysqlService.deleteUser(user);
        result += postgresqlService.deleteUser(user);
        return result;
    }

    /**
     * 删除所有用户
     *
     * @return int
     */
    public int deleteAllUsers() {
        int result = mysqlService.deleteAllUsers();
        result += postgresqlService.deleteAllUsers();
        return result;
    }
}
