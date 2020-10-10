package cn.swift.domain.user;

public interface UserRepository {

    void save(User user);
    
    User queryUser(Long userId);
    
    User queryUser(String username);
    
    void deleteUser(String username);
    
}
