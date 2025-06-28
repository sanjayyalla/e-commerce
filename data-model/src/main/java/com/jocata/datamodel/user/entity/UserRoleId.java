package com.jocata.datamodel.user.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {
    private Integer userId;
    private Integer roleId;

    public UserRoleId() {
    }

    public UserRoleId(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId)) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}