package com.putracode.springbootredis.service;

import com.putracode.springbootredis.model.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);

    List<User> fetchAllUser();

    User findById(Long id);

    boolean deleteUserById(Long id);
}
