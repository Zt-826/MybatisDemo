package com.example.mybatisdemo.service;

import com.example.mybatisdemo.bean.User;
import com.example.mybatisdemo.mapper.mysql.MysqlMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
//@Mysql
//@Transactional(rollbackFor = Exception.class, value = "transactionalManager")
@Transactional(rollbackFor = Exception.class, value = "mysqlTransactionalManager")
public class MysqlService {
    @Resource
    private MysqlMapper mysqlMapper;

    /**
     * 查询所有用户
     *
     * @return List<User>
     */
    public List<User> queryAllUsers() {
        return mysqlMapper.queryAllUsers();
    }

    /**
     * 根据条件查询用户
     *
     * @param user user
     * @return List<User>
     */
    public List<User> queryUser(User user) {
        return mysqlMapper.queryUser(user);
    }

    /**
     * 插入用户
     *
     * @param user user
     * @return int
     */
    public int insertUser(User user) {
        return mysqlMapper.insertUser(user);
    }

    /**
     * 批量插入用户
     *
     * @param users users
     * @return int
     */
    public int insertUsers(List<User> users) {
        return mysqlMapper.insertUsers(users);
    }

    /**
     * 修改用户
     *
     * @param user user
     * @return int
     */
    public int updateUser(User user) {
        return mysqlMapper.updateUser(user);
    }

    /**
     * 根据条件删除用户
     *
     * @param user user
     * @return int
     */
    public int deleteUser(User user) {
        return mysqlMapper.deleteUser(user);
    }

    /**
     * 删除所有用户
     *
     * @return int
     */
    public int deleteAllUsers() {
        return mysqlMapper.deleteAllUsers();
    }
}
