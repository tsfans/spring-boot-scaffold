package com.github.tsfans.infrastructure.db.mapper;

import com.github.tsfans.infrastructure.db.po.UserPO;

public interface UserPOMapper {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPO record);

}