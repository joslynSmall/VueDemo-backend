package com.yangyl.domain;

import java.io.Serializable;
import java.util.List;

public class RoleBindBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int roleId;
	
	private List<Integer> permissionIds;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(List<Integer> permissionIds) {
		this.permissionIds = permissionIds;
	}

	
	
}
