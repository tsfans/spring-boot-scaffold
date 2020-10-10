package cn.swift.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.swift.domain.user.User;
import cn.swift.domain.user.UserRepository;
import cn.swift.domain.user.UserService;

@Service
public class UserApplication {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    public void create(String username, String password) {
        User user = userService.create(username, password);
        userRepository.save(user);
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
