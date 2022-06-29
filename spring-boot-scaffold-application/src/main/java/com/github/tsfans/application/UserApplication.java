package com.github.tsfans.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.tsfans.domain.user.User;
import com.github.tsfans.domain.user.UserRepository;
import com.github.tsfans.domain.user.UserService;

@Service
public class UserApplication {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    public void create(String username, String password) {
        userService.create(username, password);
    }

    public User query(String username) {
        return userRepository.queryUser(username);
    }

    public void update(String username, String password) {
        User user = userRepository.queryUser(username);
        if(user != null) {
            user.setupPassword(password);
            userRepository.save(user);
        }
    }

    public void delete(String username) {
        userRepository.deleteUser(username);
    }

}
