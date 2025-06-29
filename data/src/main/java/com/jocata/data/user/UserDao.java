package com.jocata.data.user;

import com.jocata.datamodel.user.entity.User;

import java.util.List;

public interface UserDao {
    User save(User user);

    User findById(Integer id);

    User findByUsername(String username);

    List<User> findAll();

    String deleteById(Integer id);

    User findByUsernameAndPassword(String username, String password);
}
