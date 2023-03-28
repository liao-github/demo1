package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("")
    public List<User> getAllUsers() {
        System.out.println("查询全表");
        return userMapper.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        System.out.println("根据id查询数据");
        return userMapper.getUserById(id);
    }

    @PostMapping("")
    public String insertUser(@RequestBody User user) {
        System.out.println("插入一条用户数据");
        userMapper.insertUser(user);
        return "insert user success";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        User u = userMapper.getUserById(id);
        if (u != null) {
            u.setName(user.getName());
            u.setAge(user.getAge());
            u.setEmail(user.getEmail());
            userMapper.updateUser(u);
            return "update user success";
        }
        return "user not found";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        User u = userMapper.getUserById(id);
        if (u != null) {
            userMapper.deleteUser(id);
            return "delete user success";
        }
        return "user not found";
    }
}