package com.putracode.springbootredis.ctrl;

import com.putracode.springbootredis.model.User;
import com.putracode.springbootredis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        boolean result=userService.saveUser(user);
        if(result){
            return ResponseEntity.ok("User Created Successfully!!!");
        }else {
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("/user")
    public ResponseEntity<List<User>> fetchAllUser(){
        List<User> users;
        users=userService.fetchAllUser();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> fetchUserById(@PathVariable("id") Long id){
        User user;
        user=userService.findById(id);
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) {
        boolean result = userService.deleteUserById(id);
        if (result) {
            return ResponseEntity.ok("User Deleted Successfully!!!");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
