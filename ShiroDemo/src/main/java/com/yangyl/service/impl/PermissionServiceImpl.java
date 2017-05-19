package com.yangyl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yangyl.domain.Permission;
import com.yangyl.domain.Role;
import com.yangyl.service.PermissionService;
import com.yangyl.wrap.PermissionWrap;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;
import com.yyl.common.datasource.DynamicDataSourceHolder;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionWrap permissionWrap;

	@Override
	public ResponseData listPermissions(RequestBean bean) {
		// TODO Auto-generated method stub
		return permissionWrap.listPermissions(bean);
	}

	@Override
	public ResponseData updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		Permission savePermission = null;
		try {
			if (permission.getId() == 0) {
				savePermission = new Permission();
			} else {
				savePermission = permissionWrap.selectPermissionById(permission.getId());
			}
			savePermission.setPermission(permission.getPermission());
			savePermission.setDescription(permission.getDescription());
			savePermission.setAvailable(permission.getAvailable());
			if (permission.getId() == 0) {
				permissionWrap.createPermission(savePermission);
			} else {
				permissionWrap.updatePermission(savePermission);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(savePermission);
	}

	@Override
	public ResponseData deletePermission(int id) {
		// TODO Auto-generated method stub
		try {
			permissionWrap.deletePermission(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(true);
	}

	@Override
	public ResponseData selectPermissionById(int id) {
		// TODO Auto-generated method stub
		Permission permission = null;
		try {
			permission = permissionWrap.selectPermissionById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(permission);
	}

	@Override
	public ResponseData listAllPermissions() {
		// TODO Auto-generated method stub
		List<Permission> permissionList = null;
		try {
			permissionList= permissionWrap.listAllPermissions();
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(permissionList);
	}

	@Override
	public ResponseData listPermissionsByRoleId(int roleId) {
		// TODO Auto-generated method stub
		List<Integer> permissions = null;
		try {
			permissions= permissionWrap.listPermissionsByRoleId(roleId);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(permissions);
	}
	
	

	
}
