package cn.swift.infrastructure.db.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.swift.infrastructure.db.po.UserPO;

@Mapper
public interface UserMapper extends UserPOMapper {

    UserPO selectByUsername(String username);

    void deleteByUsername(String username);

}
