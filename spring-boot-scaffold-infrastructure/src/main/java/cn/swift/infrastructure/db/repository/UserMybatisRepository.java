package cn.swift.infrastructure.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.swift.domain.user.User;
import cn.swift.domain.user.UserRepository;
import cn.swift.infrastructure.db.mapper.UserMapper;
import cn.swift.infrastructure.db.po.UserPO;
import cn.swift.infrastructure.util.BeanConverter;

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
