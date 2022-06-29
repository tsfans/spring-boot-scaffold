package com.github.tsfans.domain.user;

import com.github.tsfans.domain.BaseEntity;
import lombok.Getter;

@Getter
public class User extends BaseEntity {

    private String username;
    
    private String password;
    
    public User(Long userId, String username) {
        this.setId(userId);
        this.username = username;
    }
    
    public void setupPassword(String password) {
        this.password = password;
    }
    
}
