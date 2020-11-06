package com.thoughtworks.capacity.gtb.mvc.controller;


import com.thoughtworks.capacity.gtb.mvc.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;


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

        userList.add(user);
        return ResponseEntity.status(201).build();
    }

}
