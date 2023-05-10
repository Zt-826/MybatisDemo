package com.example.mybatisdemo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.mybatisdemo.bean.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class MultiDataSourceTest {

    @Resource
    private MultiDataSourceService service;

    @BeforeEach
    void beforeEach() throws IOException {
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/user.json");
        JSONArray jsonArray = JSON.parseObject(Objects.requireNonNull(resourceAsStream), JSONArray.class);
        List<User> userList = jsonArray.toJavaList(User.class);
        int result = service.insertUsers2(userList);
        Assertions.assertEquals(10, result);
    }


    @AfterEach
    void afterEach() {
        service.deleteAllUsers2();
        List<User> resultList = service.queryAllUsers2();
        Assertions.assertEquals(0, resultList.size());
    }

    @Test
    public void queryAllUsers() {
        List<User> resultList = service.queryAllUsers2();
        Assertions.assertEquals(10, resultList.size());
    }

    @Test
    void queryUser() {
        // 根据Id和用户名查询
        User user = new User(1, "Alice");
        List<User> resultList = service.queryUser2(user);
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(resultList.size(), 2);

        // 根据Id查询
        user = new User(1);
        resultList = service.queryUser2(user);
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(resultList.size(), 2);

        // 根据用户名查询
        user = new User("Alice");
        resultList = service.queryUser2(user);
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(resultList.size(), 4);
    }

    @Test
    void insertUser() {
        User user = new User(6, "Ella", "ella");
        int result = service.insertUser2(user);
        Assertions.assertEquals(result, 2);

        List<User> resultList = service.queryAllUsers2();
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(resultList.size(), 12);
    }

    @Test
    void insertUsers() {
        User ella = new User(6, "Ella", "ella");
        User felix = new User(7, "Felix", "felix");
        User glimmer = new User(8, "Glimmer", "glimmer");
        List<User> userList = Arrays.asList(ella, felix, glimmer);
        int result = service.insertUsers2(userList);
        Assertions.assertEquals(result, 6);

        List<User> resultList = service.queryAllUsers2();
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(resultList.size(), 16);
    }

    @Test
    void updateUser() {
        // 根据Id修改用户名和密码
        User user = new User(2, "Ella", "ella");
        int result = service.updateUser2(user);
        Assertions.assertEquals(result, 2);

        List<User> resultList = service.queryUser2(user);
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(resultList.size(), 2);


        // 根据Id修改用户名
        user = new User(3, "Felix");
        result = service.updateUser2(user);
        Assertions.assertEquals(result, 2);

        resultList = service.queryUser2(user);
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(resultList.size(), 2);

        // 批量修改用户名
        user = new User("Alice");
        result = service.updateUser2(user);
        Assertions.assertEquals(result, 10);

        resultList = service.queryUser2(user);
        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(resultList.size(), 10);

    }

    @Test
    void deleteUser() {
        // 根据Id和用户名删除
        User user = new User(2, "Bob");
        int result = service.deleteUser2(user);
        Assertions.assertEquals(result, 2);

        // 根据Id删除
        user = new User(3);
        result = service.deleteUser2(user);
        Assertions.assertEquals(result, 2);

        // 根据用户名删除
        user = new User("Alice");
        result = service.deleteUser2(user);
        Assertions.assertEquals(result, 4);
    }

    @Test
    void deleteAllUsers() {
        int result = service.deleteAllUsers2();
        Assertions.assertEquals(result, 10);
    }
}
