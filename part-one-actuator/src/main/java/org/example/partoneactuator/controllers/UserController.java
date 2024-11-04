package org.example.partoneactuator.controllers;


import org.example.partoneactuator.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @GetMapping
    public List<User> getUsers(){
        return List.of(new User(1L,"Ahurein", "123"));
    }
}
