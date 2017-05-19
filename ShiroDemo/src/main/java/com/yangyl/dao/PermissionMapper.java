package com.yangyl.dao;

import java.util.List;
import java.util.Map;

import com.yangyl.domain.Permission;

public interface PermissionMapper {

	public void createPermission(Permission permission);

    public void deletePermission(int permissionId);

	public List<Permission> listPermissions(Map map);
	
	public List<Permission> listAllPermissions();

	public Permission selectPermissionById(int id);

	public void updatePermission(Permission permission);

	public List<Integer> listPermissionsByRoleId(int roleId);
}
