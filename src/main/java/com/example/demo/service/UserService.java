package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    List<User> listUser();
    User saveUser(User user);
    void deleteUser(Long id);
    User findUser(Long id);
    User updateUser(Long id, User user);

}
