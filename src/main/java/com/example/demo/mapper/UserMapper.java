package com.example.demo.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.demo.entity.User;


@Mapper

public interface UserMapper {
    ArrayList<User> getAllUsers();
    User getUserById(@Param("id") int id);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(@Param("id") int id);
}