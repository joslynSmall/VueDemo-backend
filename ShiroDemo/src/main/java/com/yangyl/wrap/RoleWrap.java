package com.yangyl.wrap;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangyl.dao.RoleMapper;
import com.yangyl.dao.UserRoleMapper;
import com.yangyl.domain.Role;
import com.yangyl.domain.User;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;

@Repository
public class RoleWrap {

	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private UserRoleMapper userRoleMapper;
	
	@Transactional(readOnly=false)
	public Role createRole(Role role) {
		roleMapper.createRole(role);
		return role;
	}
	
	@Transactional(readOnly=false)
    public Role selectRoleById(int roleId) {
		return roleMapper.selectRoleById(roleId);
    }
	
	@Transactional(readOnly=false)
    public void deleteRole(int roleId) {
		roleMapper.deleteRole(roleId);
		userRoleMapper.deleteUserRoleByRoleId(roleId);
    }
    
	@Transactional(readOnly=false)
	public void correlationPermissions(int roleId, int permissionId) {
		roleMapper.correlationPermissions(roleId, permissionId);
	}
	
	@Transactional(readOnly=false)
	public void uncorrelationPermissions(int roleId, int permissionId) {
		roleMapper.uncorrelationPermissions(roleId, permissionId);
	}

	@Transactional(readOnly=true)
	public ResponseData listRoles(RequestBean bean) {
		// TODO Auto-generated method stub
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<Role> list = roleMapper.listRoles(bean.getParam());
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		
		ResponseData result = new ResponseData(list);
		result.setPage(pageInfo.getPageNum());
		result.setPageSize(pageInfo.getPageSize());
		result.setTotal((int) pageInfo.getTotal());
		
		return result;
	}

	@Transactional(readOnly=false)
	public Role updateRole(Role role) {
		// TODO Auto-generated method stub
		roleMapper.updateRole(role);
		return role;
	}

	public List<Role> listAllRoles() {
		// TODO Auto-generated method stub
		return roleMapper.listAllRoles();
	}

	public List<Integer> listRolesByUserId(int userId) {
		// TODO Auto-generated method stub
		return roleMapper.listRolesByUserId(userId);
	}
}
