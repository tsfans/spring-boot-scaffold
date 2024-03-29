package com.github.tsfans.infrastructure.db.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.tsfans.infrastructure.db.po.UserPO;

@DisplayName("user DAO单测")
class UserMapperTest extends BaseMapperTest {
   
    @Autowired
    private UserMapper userMapper;

    @DisplayName("根据用户名查询用户")
    @Test
    void testSelectByUsername() {
        UserPO po = userMapper.selectByUsername("amy");
        assertNotNull(po);
    }

    @DisplayName("根据用户名删除用户")
    @Test
    void testDeleteByUsername() {
        int count = userMapper.deleteByUsername("bob");
        assertEquals(1, count);
    }

}
