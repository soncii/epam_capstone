package com.damir.healthcare.auth;

import com.damir.healthcare.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyUser implements UserDetails {
    private User user;
    private ArrayList<AuGroup> auGroupList;
    public MyUser(User user, ArrayList<AuGroup> auGroupList){
        this.user=user;
        this.auGroupList=auGroupList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (auGroupList==null) return new HashSet<>();
        Set<SimpleGrantedAuthority> auths = new HashSet<>();
        for (AuGroup c:auGroupList    ) {
            auths.add(new SimpleGrantedAuthority(c.getAuthgroup()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
