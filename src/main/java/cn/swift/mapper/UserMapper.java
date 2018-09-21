package cn.swift.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.swift.controllers.request.UserRequest;
import cn.swift.model.User;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUsernameAndPassword(UserRequest ur);
}