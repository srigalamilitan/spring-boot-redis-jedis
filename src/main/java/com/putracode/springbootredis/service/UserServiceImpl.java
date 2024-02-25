package com.putracode.springbootredis.service;

import com.putracode.springbootredis.model.User;
import com.putracode.springbootredis.repo.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    @Override
    public boolean saveUser(User user) {

        return userDao.saveUser(user);
    }

    @Override
    public List<User> fetchAllUser() {

        return userDao.fetchAllUser();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public boolean deleteUserById(Long id) {
        return userDao.deleteUserById(id);
    }
}
