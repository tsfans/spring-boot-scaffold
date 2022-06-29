package com.github.tsfans.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.tsfans.domain.IdentifierService;

@Service
public class UserService {

    @Autowired
    private IdentifierService identifierService;
    
    @Autowired
    private UserRepository userRepository;
    
    public void create(String username, String password) {
        User existedUser = userRepository.queryUser(username);
        if(existedUser != null) {
            return;
        }
        User user = new User(identifierService.generateIdentifier(), username);
        user.setupPassword(password);
        userRepository.save(user);
    }
}
