package com.yangyl.wrap;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangyl.dao.PermissionMapper;
import com.yangyl.dao.RolePermissionMapper;
import com.yangyl.domain.Permission;
import com.yangyl.domain.Role;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;

@Repository
public class PermissionWrap {

	@Resource
	private PermissionMapper permissionMapper;
	
	@Resource
	private RolePermissionMapper rolePermissionMapper;
	
	@Transactional(readOnly=false)
	public Permission createPermission(Permission permission) {
		permissionMapper.createPermission(permission);
		return permission;
	}
	
	@Transactional(readOnly=false)
    public void deletePermission(int permissionId) {
		permissionMapper.deletePermission(permissionId);
		rolePermissionMapper.deleteRolePermissionByPermissionId(permissionId);
    }

	@Transactional(readOnly=true)
	public Permission selectPermissionById(int id) {
		// TODO Auto-generated method stub
		return permissionMapper.selectPermissionById(id);
	}

	@Transactional(readOnly=true)
	public ResponseData listPermissions(RequestBean bean) {
		// TODO Auto-generated method stub
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Permission> list = permissionMapper.listPermissions(bean.getParam());
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(list);
		
		ResponseData result = new ResponseData(list);
		result.setPage(pageInfo.getPageNum());
		result.setPageSize(pageInfo.getPageSize());
		result.setTotal((int) pageInfo.getTotal());
		
		return result;
	}

	@Transactional(readOnly=false)
	public void updatePermission(Permission permission) {
		// TODO Auto-generated method stub
		permissionMapper.updatePermission(permission);
	}

	public List<Permission> listAllPermissions() {
		// TODO Auto-generated method stub
		return permissionMapper.listAllPermissions();
	}

	public List<Integer> listPermissionsByRoleId(int roleId) {
		// TODO Auto-generated method stub
		return permissionMapper.listPermissionsByRoleId(roleId);
	}
}
