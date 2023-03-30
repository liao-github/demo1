package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import static com.example.demo.fun.HandleController.handleUser_test1;
import static com.example.demo.fun.HandleController.handleUser_test3;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("")
    public ArrayList<User> getAllUsers(@RequestParam("agemax") int agemax, @RequestParam("target") int target) {
        System.out.println("查询全表");
        ArrayList<User> users =  userMapper.getAllUsers();
        /**
         * test1. 参数：搜索名称含有明的用户们
         * test2. 年龄 < 25
         * test3. 找出两个年纪之和为50的人员
         *
         * */
//        List<User> res = null;
//        for (User u: users) {
//            if (u.name.contains('明'))  res.add(u);
//        }
        for (User user : users) {
            System.out.println("姓名: "+user.getName());
            System.out.println("年龄: "+user.getAge());
            System.out.println("id:   "+user.getId());
            System.out.println("邮箱: "+user.getEmail());
        }
        users = handleUser_test3(users, target);

        System.out.println("+++++++++处理数据后+++++++++");
        for (User user : users) {
            System.out.println("姓名: "+user.getName());
            System.out.println("年龄: "+user.getAge());
            System.out.println("id:   "+user.getId());
            System.out.println("邮箱: "+user.getEmail());
        }
        return users;
    }

    @GetMapping("/{id}")
    // 路径参数（变量）
    // 接收参数
    public User getUserById(@PathVariable int id) {
        System.out.println("根据id查询数据");
        return userMapper.getUserById(id);
    }

    @PostMapping("/test")
    public String insertTestUser(@RequestParam("name") String name, @RequestParam("age") int age, @RequestParam("email") String email, @RequestBody User user) {
        System.out.println("插入一条用户数据");

        System.out.println(user);

        userMapper.insertUser(user);
        return "insert user success";
    }
    @PostMapping("")
    public String insertUser(@RequestBody User user){
        System.out.println("插入一条用户数据");
        System.out.println(user);

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