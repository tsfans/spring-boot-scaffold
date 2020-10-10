package cn.swift.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.swift.domain.IdentifierService;

@Service
public class UserService {

    @Autowired
    private IdentifierService identifierService;
    
    public User create(String username, String password) {
        User user = new User(identifierService.generateIdentifier(), username);
        user.setupPassword(password);
        return user;
    }
}
