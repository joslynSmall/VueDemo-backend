package com.yangyl.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yangyl.domain.Role;
import com.yangyl.domain.RoleBindBean;
import com.yangyl.domain.UserBindBean;
import com.yangyl.service.RoleService;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@RequestMapping(value="/table", method=RequestMethod.GET)
	public ResponseData listRoles (RequestBean bean) {
		return roleService.listRoles(bean);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseData listAllRoles () {
		return roleService.lisAlltRoles();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseData updateRole (@RequestBody Role role) {
		return roleService.updateRole(role);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseData deleteRoleById(@PathVariable int id) {
		return roleService.deleteRole(id);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResponseData listRolesByUserId(int userId) {
		return roleService.listRolesByUserId(userId);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseData selectRoleById(@PathVariable int id) {
		return roleService.selectRoleById(id);
	}
	
	@RequestMapping(value="/bind", method=RequestMethod.POST)
	public ResponseData bindPermissions(@RequestBody RoleBindBean bean) {
		return roleService.bindPermissions(bean.getRoleId(),bean.getPermissionIds());
	}
}
