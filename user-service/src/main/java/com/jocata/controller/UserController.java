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
        if (form.getUsername() != null && !form.getUsername().isEmpty()
                && form.getPassword() != null && !form.getPassword().isEmpty()) {
            return userService.createUser(form);
        } else {
            UserForm userForm = new UserForm();
            userForm.setMessage("Please enter username and password correctly");
            return userForm;
        }
    }

    @GetMapping("/byUserName")
    public UserForm getUserByUserName(@RequestParam String userName) {

        if (userName != null && !userName.isEmpty()) {
            return userService.getUserByUserName(userName);
        } else {
            UserForm userForm = new UserForm();
            userForm.setMessage("Please enter username");
            return userForm;
        }

    }

    @GetMapping("/{id}")
    public UserForm getUser(@PathVariable String id) {
        if (id != null && !id.isEmpty()) {
            return userService.getUserById(id);
        } else {
            UserForm userForm = new UserForm();
            userForm.setMessage("Please provide user id");
            return userForm;
        }
    }

    @PostMapping("/all")
    public List<UserForm> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        if (id != null && !id.isEmpty()) {
            return userService.deleteUser(id);
        }
        return "Provide user id to delete";
    }

    @GetMapping("/login")
    public UserForm login(@RequestParam String username, @RequestParam String password) {
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            return userService.authenticateUser(username, password);
        } else {
            UserForm response = new UserForm();
            response.setMessage("Username and password must be provided");
            return response;
        }
    }
}
