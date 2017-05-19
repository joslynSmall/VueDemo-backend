package com.yangyl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yangyl.domain.Role;
import com.yangyl.domain.User;
import com.yangyl.service.RoleService;
import com.yangyl.shiro.ShiroSessionUtil;
import com.yangyl.wrap.PermissionWrap;
import com.yangyl.wrap.RoleWrap;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;
import com.yyl.common.entity.Constant;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleWrap roleWrap;
	
	@Resource
	private PermissionWrap permissionWrap;
	
	@Override
	public ResponseData updateRole(Role role) {
		// TODO Auto-generated method stub
		Role saveRole = null;
		try {
			if (role.getId() == 0) {
				saveRole = new Role();
			} else {
				saveRole = roleWrap.selectRoleById(role.getId());
			}
			saveRole.setRole(role.getRole());
			saveRole.setDescription(role.getDescription());
			saveRole.setAvailable(role.getAvailable());
			
			if (role.getAvailable() == null) {
				saveRole.setAvailable(true);
			}
			if (role.getId() == 0) {
				role = roleWrap.createRole(role);
			} else {
				role = roleWrap.updateRole(role);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(role);
	}

	@Override
	public ResponseData deleteRole(int roleId) {
		// TODO Auto-generated method stub
		try {
			roleWrap.deleteRole(roleId);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(true);
	}
	
	@Override
	public ResponseData selectRoleById(int id) {
		// TODO Auto-generated method stub
		Role role = null;
		try {
			role= roleWrap.selectRoleById(id);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(role);
	}

	@Override
	public ResponseData listRoles(RequestBean bean) {
		// TODO Auto-generated method stub
		return roleWrap.listRoles(bean);
	}

	@Override
	public ResponseData lisAlltRoles() {
		// TODO Auto-generated method stub
		List<Role> roleList = null;
		try {
			roleList= roleWrap.listAllRoles();
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(roleList);
	}

	@Override
	public ResponseData listRolesByUserId(int userId) {
		// TODO Auto-generated method stub
		List<Integer> roles = null;
		try {
			roles= roleWrap.listRolesByUserId(userId);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(roles);
	}

	@Override
	public ResponseData bindPermissions(int roleId, List<Integer> permissionIds) {
		// TODO Auto-generated method stub
		try {		
			//获取用户已有权限
			List<Integer> ids = permissionWrap.listPermissionsByRoleId(roleId);
			//提交的权限过滤已有权限，进行新增绑定
			permissionIds.stream().forEach(id -> {
				if(!ids.contains(id)) {
					roleWrap.correlationPermissions(roleId, id);
				} 
			});
			//已有角色过滤提交权限，进行删除的权限解除绑定
			ids.stream().forEach(id -> {
				if(!permissionIds.contains(id)) {
					roleWrap.uncorrelationPermissions(roleId, id);
				} 
			});
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(true);
	}

	

	

}
