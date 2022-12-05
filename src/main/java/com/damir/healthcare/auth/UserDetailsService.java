package com.damir.healthcare.auth;

import com.damir.healthcare.entities.User;
import com.damir.healthcare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
    public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuGroupRepository auGroupRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if (!user.isPresent()){
            throw new UsernameNotFoundException(username);
        }
        ArrayList<AuGroup> byId = auGroupRepository.findAllByEmail(username);
        if(byId==null){
            throw new UsernameNotFoundException(username);
        }
        return new MyUser(user.get(),byId);

    }
}
