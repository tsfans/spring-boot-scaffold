package cn.swift.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.swift.controllers.request.UserRequest;
import cn.swift.mapper.UserMapper;
import cn.swift.model.User;
import cn.swift.model.document.UserDocument;
import cn.swift.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private UserRepository userRepo;

  public boolean login(UserRequest ur) {
    User user = userMapper.findByUsernameAndPassword(ur);
    if (user != null) {
      userRepo.save(new UserDocument(user.getId().toString(), user.getUsername()));
      return true;
    }
    return false;
  }

  public List<UserDocument> searchByUsername(String username) {
    QueryStringQueryBuilder query = new QueryStringQueryBuilder(username);
    Iterable<UserDocument> iterator = userRepo.search(query);
    List<UserDocument> list = new ArrayList<>();
    iterator.forEach(u -> list.add(u));
    return list;
  }

  public UserDocument searchByUserId(String id) {
    Optional<UserDocument> userDoc = userRepo.findById(id);
    return userDoc.orElse(null);
  }

  public void addUser(UserRequest ur) {
    if (!login(ur)) {
      userMapper.addUser(ur);
    }
  }


}
