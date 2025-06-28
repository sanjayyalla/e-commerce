package com.jocata.controller;

import com.jocata.datamodel.user.form.UserForm;
import com.jocata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserForm createUser(@RequestBody UserForm form) {
        return userService.createUser(form);
    }

    @GetMapping("/{id}")
    public UserForm getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    public List<UserForm> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
