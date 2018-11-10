package cn.swift.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.swift.SpringBootScaffoldApplicationTests;
import cn.swift.model.User;

@DisplayName("Test UserMapper")
public class UserMapperTest extends SpringBootScaffoldApplicationTests{

    @Autowired
    private UserMapper userMapper;
    
    private static User user;
    
    @BeforeAll
    static void setUp() {
	user = new User();
	user.setUsername("TEST_USER");
	user.setPassword("123456");
	user.setEmail("test@gamil.com");
	user.setDescription("this is a user for test.");
    }
    
    @Test
    @DisplayName("Add user")
    void addUserTest() {
	assertAll("Insert User Success.",
		() -> assertNotNull(userMapper.insertSelective(user)),
		() -> assertNotNull(user.getId()));
    }
    
    @Test
    @DisplayName("Delete user")
    void deleteUserTest() {
	assertEquals(0, userMapper.deleteByPrimaryKey(-1L));
    }
    
    @Test
    @DisplayName("Modify user")
    void modifyUserTest() {
	user.setId(-1L);
	assertEquals(0, userMapper.updateByPrimaryKeySelective(user));
    }
    
    @Test
    @DisplayName("Query user")
    void queryUserTest() {
	assertNull(userMapper.selectByPrimaryKey(-1L));
    }
}
