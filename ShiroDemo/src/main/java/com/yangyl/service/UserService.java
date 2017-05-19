package com.yangyl.service;

import java.util.List;
import java.util.Set;

import com.yangyl.domain.LoginBean;
import com.yangyl.domain.User;
import com.yyl.common.collection.RequestBean;
import com.yyl.common.collection.ResponseData;

public interface UserService {

	/**
     * 创建用户
     * @param user
     */
    public ResponseData updateUser(User user);
    
    /**
     * 列出所有用户
     * @param user
     */
    public ResponseData listUsers(RequestBean bean);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

	public ResponseData deleteUserById(int id);

	public ResponseData selectUserById(int id);

	public ResponseData logout();

	public ResponseData login(LoginBean bean);

	public ResponseData bindRoles(int userId, List<Integer> roleIds);

}
