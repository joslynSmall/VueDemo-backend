package com.yangyl.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.yangyl.domain.LoginBean;
import com.yangyl.domain.Permission;
import com.yangyl.domain.User;
import com.yangyl.service.UserService;
import com.yangyl.wrap.PermissionWrap;
import com.yangyl.wrap.RoleWrap;
import com.yangyl.wrap.UserWrap;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;
import com.yyl.common.datasource.DynamicDataSourceHolder;
import com.yyl.common.utils.rsa.MD5;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserWrap userWrap;
	
	@Resource
	private RoleWrap roleWrap;
	
	@Resource
	private PermissionWrap permissionWrap;

	@Override
	public ResponseData updateUser(User user) {
		// TODO Auto-generated method stub
		User saveUser = null;
		try {
			if (user.getId() == 0) {
				saveUser = new User();
				saveUser.setPassword(MD5.md5(user.getPassword()));
			} else {
				saveUser = userWrap.selectUserById(user.getId());
			}
			saveUser.setUsername(user.getUsername());
			saveUser.setSalt(user.getSalt());
			saveUser.setLocked(user.getLocked());
			
			if (user.getLocked() == null) {
				saveUser.setLocked(false);
			}
			if (user.getId() == 0) {
				user = userWrap.createUser(saveUser);
			} else {
				user = userWrap.updateUser(saveUser);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(user);
	}

	@Override
	public void changePassword(Long userId, String newPassword) {
		// TODO Auto-generated method stub
		User user = loadUser(userId);
		user.setPassword(MD5.md5(user.getPassword()));
		submitUser(user);
	}

	private void submitUser(User user) {
		// TODO Auto-generated method stub
		userWrap.updateUser(user);
	}

	private User loadUser(Long userId) {
		// TODO Auto-generated method stub
		return userWrap.findOne(userId);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userWrap.findByUsername(username);
	}

	@Override
	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		List<String> roles = userWrap.findRoles(username);
		Set<String> setRoles = new HashSet<String>();
		setRoles.addAll(roles);
		return setRoles;
	}

	@Override
	public Set<String> findPermissions(String username) {
		// TODO Auto-generated method stub
		List<String> permissions = userWrap.findPermissions(username);
		Set<String> setPermissions = new HashSet<String>();
		setPermissions.addAll(permissions);
		return setPermissions;
	}

	@Override
	public ResponseData listUsers(RequestBean bean) {
		// TODO Auto-generated method stub
		
		return userWrap.listUsers(bean);
	}

	@Override
	public ResponseData deleteUserById(int id) {
		// TODO Auto-generated method stub
		try {
			userWrap.deleteUserById(id);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(true);
	}

	@Override
	public ResponseData selectUserById(int id) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			user = userWrap.selectUserById(id);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(user);
	}

	@Override
	public ResponseData logout() {
		// TODO Auto-generated method stub
		try {
			Subject currentUser = SecurityUtils.getSubject();       
	        currentUser.logout();  
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(true);
	}

	@Override
	public ResponseData login(LoginBean bean) {
		// TODO Auto-generated method stub
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(bean.getUsername(), MD5.md5(bean.getPassword()));
			Subject currentUser = SecurityUtils.getSubject();  
	        if (!currentUser.isAuthenticated()){
	            //使用shiro来验证  
	            currentUser.login(token);//验证角色和权限  
	        } 
		} catch (UnknownAccountException e) {
        	return new ResponseData(false, "账号不存在");
        } catch (LockedAccountException e) {
        	return new ResponseData(false, "账号被锁定");
        } catch (IncorrectCredentialsException e) {
        	return new ResponseData(false, "账号或密码错误");
        } 
		//获取用户权限
		List<String> permission = userWrap.findPermissions(bean.getUsername());
		List<Permission> allPermission = permissionWrap.listAllPermissions();
		Map<String,Boolean> permissionMap = new HashMap<String,Boolean>();
		allPermission.stream().forEach(p -> {
			if (permission.contains(p.getPermission())) {
				permissionMap.put(p.getPermission(), true);
			} else {
				permissionMap.put(p.getPermission(), false);
			}
		});
		
	   return new ResponseData(permissionMap);
	}

	@Override
	public ResponseData bindRoles(int userId, List<Integer> roleIds) {
		// TODO Auto-generated method stub
		try {		
			//获取用户已有角色
			List<Integer> ids = roleWrap.listRolesByUserId(userId);
			//提交的角色过滤已有角色，进行新增绑定
			roleIds.stream().forEach(id -> {
				if(!ids.contains(id)) {
					userWrap.correlationRoles(userId, id);
				} 
			});
			//已有角色过滤提交角色，进行删除的角色解除绑定
			ids.stream().forEach(id -> {
				if(!roleIds.contains(id)) {
					userWrap.uncorrelationRoles(userId, id);
				} 
			});
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseData(false, e.getMessage());
		}
		return new ResponseData(true);
	}
	
	
}
