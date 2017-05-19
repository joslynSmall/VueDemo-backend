package com.yangyl.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yangyl.domain.LoginBean;
import com.yangyl.domain.User;
import com.yangyl.service.UserService;
import com.yyl.common.collection.ResponseData;

/**
 * 账户管理(登录/注册/权限跳转/)
 */
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseData register(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseData checkLogin(@RequestBody LoginBean bean) {
		return userService.login(bean);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ResponseData logout() {
		return userService.logout();
	}
	
	@RequestMapping("/noLogin")
	public ResponseData noLogin() {
		return new ResponseData(521,"用户未登录");
	}
}
