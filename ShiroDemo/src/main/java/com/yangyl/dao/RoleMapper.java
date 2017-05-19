package com.yangyl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yangyl.domain.Role;
import com.yyl.common.collection.ResponseData;

public interface RoleMapper {

	void createRole(Role role);
    void deleteRole(int roleId);
    void updateRole(Role role);   
    Role selectRoleById(int roleId);

    void correlationPermissions(@Param("roleId")int roleId, @Param("permissionId")int permissionId);
    
    void uncorrelationPermissions(@Param("roleId")int roleId, @Param("permissionId")int permissionId);
	
    List<Role> listRoles(Map map);
    
    List<Role> listAllRoles();
    
	List<Integer> listRolesByUserId(int userId);
	
	
}
