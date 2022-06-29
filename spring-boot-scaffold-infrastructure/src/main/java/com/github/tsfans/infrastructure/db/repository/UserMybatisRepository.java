package com.github.tsfans.infrastructure.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.tsfans.domain.user.User;
import com.github.tsfans.domain.user.UserRepository;
import com.github.tsfans.infrastructure.db.mapper.UserMapper;
import com.github.tsfans.infrastructure.db.po.UserPO;
import com.github.tsfans.infrastructure.util.BeanConverter;

@Service
public class UserMybatisRepository extends BaseRepository implements UserRepository {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public void save(User user) {
        UserPO userPO = BeanConverter.convertToUserPO(user);
        userPO.setVersion(userPO.getVersion() + 1);
        if(isInsert(user)) {
            userMapper.insertSelective(userPO);
        } else {
            userMapper.updateByPrimaryKeySelective(userPO);
        }
    }

    @Override
    public User queryUser(Long userId) {
        UserPO userPO = userMapper.selectByPrimaryKey(userId);
        if(userPO == null) {
            return null;
        }
        return BeanConverter.convertToUser(userPO);
    }

    @Override
    public User queryUser(String username) {
        UserPO userPO = userMapper.selectByUsername(username);
        if(userPO == null) {
            return null;
        }
        return BeanConverter.convertToUser(userPO);
    }

    @Override
    public void deleteUser(String username) {
        userMapper.deleteByUsername(username);
    }

}
