package com.example.mybatisdemo.service;

import com.example.mybatisdemo.bean.User;
import com.example.mybatisdemo.mapper.MybatisDemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class BatchInsertTest {
    private static final int DATA_SIZE = 10000;

    private static final int BATCH_SIZE = 1000;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Resource
    private MybatisDemoMapper mybatisDemoMapper;

    @AfterEach
    void afterEach() {
        mybatisDemoMapper.deleteAllUsers();
    }

    @Test
    void insertUserByLoop() {
        List<User> users = generateData();
        long startTime = System.currentTimeMillis();
        int result = 0;
        for (User user : users) {
            result += mybatisDemoMapper.insertUser(user);
        }
        log.info("cost time {}", System.currentTimeMillis() - startTime);
        Assertions.assertEquals(result, DATA_SIZE);
    }

    @Test
    void insertUsersByForeach() {
        List<User> users = generateData();
        long startTime = System.currentTimeMillis();
        int result = mybatisDemoMapper.insertUsers(users);
        log.info("cost time {}", System.currentTimeMillis() - startTime);
        Assertions.assertEquals(result, DATA_SIZE);
    }

    @Test
    void insertUserByBatch() {
        List<User> users = generateData();
        long startTime = System.currentTimeMillis();
        // 批处理方式，关闭自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        MybatisDemoMapper mapper = sqlSession.getMapper(MybatisDemoMapper.class);
        for (int i = 0; i < users.size(); i++) {
            mapper.insertUser(users.get(i));
            // 每1000条数据提交一次
            if ((i + 1) % BATCH_SIZE == 0) {
                sqlSession.commit();
            }
        }
        sqlSession.commit();
        log.info("cost time {}", System.currentTimeMillis() - startTime);
        Assertions.assertEquals(mybatisDemoMapper.queryAllUsers().size(), DATA_SIZE);
    }

    List<User> generateData() {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= DATA_SIZE; i++) {
            userList.add(new User(i, "username" + i, "password" + i));
        }
        return userList;
    }
}
