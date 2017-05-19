package com.yangyl.service;

import com.yangyl.domain.Permission;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;

public interface PermissionService {

	public ResponseData listPermissions(RequestBean bean);

	public ResponseData updatePermission(Permission permission);

	public ResponseData deletePermission(int id);

	public ResponseData selectPermissionById(int id);

	public ResponseData listAllPermissions();

	public ResponseData listPermissionsByRoleId(int roleId);
}
