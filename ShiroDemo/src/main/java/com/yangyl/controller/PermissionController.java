package com.yangyl.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yangyl.domain.Permission;
import com.yangyl.service.PermissionService;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;

@RestController
@RequestMapping("/permission")
public class PermissionController {

	@Resource
	private PermissionService permissionService;
	
	@RequestMapping(value="/table", method=RequestMethod.GET)
	public ResponseData listPermissions (RequestBean bean) {
		return permissionService.listPermissions(bean);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseData updatePermission (@RequestBody Permission Permission) {
		return permissionService.updatePermission(Permission);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseData deletePermissionById(@PathVariable int id) {
		return permissionService.deletePermission(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseData selectPermissionById(@PathVariable int id) {
		return permissionService.selectPermissionById(id);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseData listAllPermissions () {
		return permissionService.listAllPermissions();
	}
	
	@RequestMapping(value="/role", method=RequestMethod.GET)
	public ResponseData listPermissionsByRoleId(int roleId) {
		return permissionService.listPermissionsByRoleId(roleId);
	}
}
