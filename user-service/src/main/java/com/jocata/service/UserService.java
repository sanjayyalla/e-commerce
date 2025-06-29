package com.jocata.service;

import com.jocata.datamodel.user.form.UserForm;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    public UserForm createUser(@RequestBody UserForm form);

    UserForm getUserById(String id);

    List<UserForm> getAllUsers();

    String deleteUser(String id);

    UserForm getUserByUserName(String userName);

    UserForm authenticateUser(String username, String password);
}
