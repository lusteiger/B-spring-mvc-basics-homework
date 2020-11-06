package com.thoughtworks.capacity.gtb.mvc.controller;


import com.thoughtworks.capacity.gtb.mvc.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class UserController {

    static ArrayList<User> userList = new ArrayList<>();


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody @Validated User user) throws Exception {
        boolean exist = false;
        for (User value : userList) {
            if (value.getUsername().equals(user.getUsername())) {
                exist = true;
                break;
            }
        }
        if (exist) {
            throw new Exception("user is exist");
        }
        user.setId(userList.size() + 1);
        userList.add(user);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {

        for (User value : userList) {
            if ((value.getUsername().equals(username)) &&
                    (value.getPassword().equals(password))) {
                return ResponseEntity.status(200).body(value);
            }
        }
        return ResponseEntity.badRequest().build();
    }

}
