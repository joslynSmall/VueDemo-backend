package com.yangyl.wrap;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangyl.dao.UserMapper;
import com.yangyl.domain.User;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;
import com.yyl.common.utils.rsa.MD5;

@Repository
public class UserWrap {

	@Resource
	private UserMapper userMapper;
	
	@Transactional(readOnly=false)
	public User createUser(User user) {
		userMapper.createUser(user);
		return user;
	}
	
	@Transactional(readOnly=false)
	public User updateUser(User user) {
		userMapper.updateUser(user);
		return user;
	}
	
	@Transactional(readOnly=false)
	public void correlationRoles(int userId, int roleId) {
		userMapper.correlationRoles(userId, roleId);
	}
	
	@Transactional(readOnly=false)
	public void uncorrelationRoles(int userId, int roleId) {
		userMapper.uncorrelationRoles(userId, roleId);
	}
	
	@Transactional(readOnly=true)
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	
	@Transactional(readOnly=true)
	public User findOne(Long userId) {
		return userMapper.findOne(userId);
	}
	
	@Transactional(readOnly=true)
	public List<String> findRoles(String username) {
		return userMapper.findRoles(username);
	}
	
	@Transactional(readOnly=true)
	public List<String> findPermissions(String username) {
		return userMapper.findPermissions(username);
	}

	@Transactional(readOnly=true)
	public ResponseData listUsers(RequestBean bean) {
		// TODO Auto-generated method stub
		PageHelper.startPage(bean.getPage(), bean.getRows());
		List<User> list = userMapper.select(bean.getParam());
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		
		ResponseData result = new ResponseData(list);
		result.setPage(pageInfo.getPageNum());
		result.setPageSize(pageInfo.getPageSize());
		result.setTotal((int) pageInfo.getTotal());
		
		return result;
	}

	@Transactional(readOnly=false)
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		userMapper.deleteUser(id);
		
	}
	
	@Transactional(readOnly=true)
	public User selectUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectUserById(id);
	}
	
}
