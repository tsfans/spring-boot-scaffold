package com.github.tsfans.infrastructure.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.tsfans.infrastructure.db.po.UserPO;

@Mapper
public interface UserMapper extends UserPOMapper {

    UserPO selectByUsername(@Param("username") String username);

    int deleteByUsername(@Param("username") String username);

}
