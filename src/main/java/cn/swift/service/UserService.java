package cn.swift.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.swift.controllers.request.UserRequest;
import cn.swift.mapper.UserMapper;
import cn.swift.model.User;

@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  public boolean login(UserRequest ur) {
    User user = userMapper.findByUsernameAndPassword(ur);
    return user!=null;
  }

  public void addUser(UserRequest ur) {
    if (!login(ur)) {
      userMapper.addUser(ur);
    }
  }


}
