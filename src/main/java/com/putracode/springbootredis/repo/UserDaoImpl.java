package com.putracode.springbootredis.repo;

import com.putracode.springbootredis.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserDaoImpl implements UserDao {
    private final RedisTemplate redisTemplate;
    private static final String KEY="USER";
    @Override
    public boolean saveUser(User user) {
        try {
            redisTemplate.opsForHash().put(KEY,user.getId().toString(),user);
            return true;
        }catch (Exception e){
            log.error("Error Save user",e);
            return false;
        }
    }

    @Override
    public List<User> fetchAllUser() {
        List<User> users;
        users=redisTemplate.opsForHash().values(KEY);
        return users;
    }

    @Override
    public User findById(Long id) {
        return (User) redisTemplate.opsForHash().get(KEY,id.toString());

    }

    @Override
    public boolean deleteUserById(Long id) {
        try {
            redisTemplate.opsForHash().delete(KEY,id.toString());
            return true;
        }catch (Exception e){
            log.error("Error Save user",e);
            return false;
        }
    }
}
