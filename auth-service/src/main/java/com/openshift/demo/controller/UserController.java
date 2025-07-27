package com.openshift.demo.controller;

import java.util.List;
import com.openshift.demo.model.*;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.auth.userService.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
    private UserRepository repo;

    @PostMapping
    public User add(@RequestBody User user) {
        return repo.save(user);
    }

    @GetMapping
    public List<User> list() {
        return repo.findAll();
    }
}
