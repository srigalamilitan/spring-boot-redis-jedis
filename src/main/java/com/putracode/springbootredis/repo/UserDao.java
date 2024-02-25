package com.putracode.springbootredis.repo;

import com.putracode.springbootredis.model.User;

import java.util.List;

public interface UserDao {
    boolean saveUser(User user);

    List<User> fetchAllUser();

    User findById(Long id);

    boolean deleteUserById(Long id);
}
