package com.yangyl.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.yangyl.domain.User;
import com.yangyl.domain.UserBindBean;
import com.yangyl.service.UserService;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ResponseData updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@RequestMapping(value="/bind", method=RequestMethod.POST)
	public ResponseData bindRoles(@RequestBody UserBindBean bean) {
		return userService.bindRoles(bean.getUserId(),bean.getRoleIds());
	}
	
	@RequestMapping(value="/table", method=RequestMethod.GET)
	@CrossOrigin
	public ResponseData listUsers(RequestBean bean) {
		return userService.listUsers(bean);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseData deleteUserById(@PathVariable int id) {
		return userService.deleteUserById(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseData selectUserById(@PathVariable int id) {
		return userService.selectUserById(id);
	}
	
	
	
}
