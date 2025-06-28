package com.jocata.service.impl;

import com.jocata.data.user.RoleDao;
import com.jocata.data.user.UserDao;
import com.jocata.data.user.UserRoleDao;
import com.jocata.datamodel.user.entity.Role;
import com.jocata.datamodel.user.entity.User;
import com.jocata.datamodel.user.entity.UserAddress;
import com.jocata.datamodel.user.entity.UserRole;
import com.jocata.datamodel.user.form.UserAddressForm;
import com.jocata.datamodel.user.form.UserForm;
import com.jocata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;


    @Override
    public UserForm createUser(UserForm form) {
        Role role = roleDao.findById(Integer.parseInt(form.getRoleId()));
        if (role == null) {
            throw new IllegalArgumentException("Invalid role ID");
        }

        User user = new User();
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        user.setRole(role);

        UserAddressForm addressForm = form.getAddressForm();
        UserAddress address = new UserAddress();
        address.setAddressLine1(addressForm.getAddressLine1());
        address.setAddressLine2(addressForm.getAddressLine2());
        address.setCity(addressForm.getCity());
        address.setPostalCode(addressForm.getPostalCode());
        address.setCountry(addressForm.getCountry());
        address.setUser(user);
        user.setAddress(address);

        User savedUser = userDao.save(user);

        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(role);
        userRoleDao.save(userRole);

        return entityToForm(savedUser);
    }

    private UserForm entityToForm(User user) {
        UserForm form = new UserForm();
        form.setId(String.valueOf(user.getId()));
        form.setUsername(user.getUsername());
        form.setPassword(user.getPassword());
        form.setEmail(user.getEmail());
        form.setCreatedAt(user.getCreatedAt() != null ? user.getCreatedAt().toString() : null);
        form.setUpdatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null);
        form.setRoleId(user.getRole() != null ? String.valueOf(user.getRole().getId()) : null);

        if (user.getAddress() != null) {
            UserAddress addr = user.getAddress();
            UserAddressForm addressForm = new UserAddressForm();
            addressForm.setAddressLine1(addr.getAddressLine1());
            addressForm.setAddressLine2(addr.getAddressLine2());
            addressForm.setCity(addr.getCity());
            addressForm.setPostalCode(addr.getPostalCode());
            addressForm.setCountry(addr.getCountry());
            addressForm.setCreatedAt(addr.getCreatedAt() != null ? addr.getCreatedAt().toString() : null);
            addressForm.setUpdatedAt(addr.getUpdatedAt() != null ? addr.getUpdatedAt().toString() : null);
            form.setAddressForm(addressForm);
        }

        return form;
    }
    @Override
    public UserForm getUserById(String id) {
        User user = userDao.findById(Integer.parseInt(id));
        return user != null ? entityToForm(user) : null;
    }

    @Override
    public List<UserForm> getAllUsers() {
        return userDao.findAll().stream()
                .map(this::entityToForm)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteUser(String id) {
        return userDao.deleteById(Integer.parseInt(id));
    }
}
