package com.lcwd.user.service;

import com.lcwd.user.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(String userId);
}
