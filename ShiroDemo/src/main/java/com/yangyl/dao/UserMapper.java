package com.yangyl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yangyl.domain.User;

public interface UserMapper {

	void createUser(User user);
    void updateUser(User user);
    void deleteUser(int id);

    void correlationRoles(@Param("userId")int userId, @Param("roleId")int roleId);
    
    void uncorrelationRoles(@Param("userId")int userId, @Param("roleId")int roleId);

    User findOne(Long userId);

    User findByUsername(String username);

    List<String> findRoles(String username);

    List<String> findPermissions(String username);
    
	List<User> select(Map<String, String> param);
	
	User selectUserById(int id);
}
