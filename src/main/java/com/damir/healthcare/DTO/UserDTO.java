package com.damir.healthcare.DTO;

import com.damir.healthcare.entities.User;

public class UserDTO {
    User user;
    String degree;

    public UserDTO(User user, String spec) {
        this.user = user;
        this.degree = spec;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSpec() {
        return degree;
    }

    public void setSpec(String spec) {
        this.degree = spec;
    }
}
