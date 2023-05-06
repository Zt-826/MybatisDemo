package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.bean.User;
import com.example.mybatisdemo.service.MybatisDemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MybatisDemoController {

    @Resource
    private MybatisDemoService mybatisDemoService;

    @RequestMapping("/queryAllUsers")
    public List<User> queryAllUsers() {
        return mybatisDemoService.queryAllUsers();
    }

    @RequestMapping("/queryUser")
    public List<User> queryUser(User user) {
        return mybatisDemoService.queryUser(user);
    }
}
