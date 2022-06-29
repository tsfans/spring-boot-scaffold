package cn.swift.infrastructure.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.swift.infrastructure.db.po.UserPO;

@Mapper
public interface UserMapper extends UserPOMapper {

    UserPO selectByUsername(@Param("username") String username);

    int deleteByUsername(@Param("username") String username);

}
