package cn.swift.infrastructure.db.mapper;

import cn.swift.infrastructure.db.po.UserPO;

public interface UserPOMapper {
    
    int deleteByPrimaryKey(Long id);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPO record);

}