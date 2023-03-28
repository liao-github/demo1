package com.example.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();
    User getUserById(@Param("id") int id);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(@Param("id") int id);
}