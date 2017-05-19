package com.yangyl.service;

import java.util.List;

import com.yangyl.domain.Role;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;

public interface RoleService {

	public ResponseData updateRole(Role role);
    public ResponseData deleteRole(int roleId);
    public ResponseData listRoles(RequestBean bean);
	public ResponseData selectRoleById(int id);

	public ResponseData lisAlltRoles();
	
	public ResponseData listRolesByUserId(int userId);
	
	public ResponseData bindPermissions(int roleId, List<Integer> permissionIds);
}
