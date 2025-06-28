package com.jocata.datamodel.user.form;

import java.util.List;

public class UserForm {

    private String id;
    private String username;
    private String password;
    private String email;
    private String createdAt;
    private String updatedAt;
    private UserAddressForm addressForm;

//    private PermissionForm permissionForm;
    private String roleId;

//    public PermissionForm getPermissionForm() {
//        return permissionForm;
//    }
//
//    public void setPermissionForm(PermissionForm permissionForm) {
//        this.permissionForm = permissionForm;
//    }

    public UserAddressForm getAddressForm() {
        return addressForm;
    }

    public void setAddressForm(UserAddressForm addressForm) {
        this.addressForm = addressForm;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

