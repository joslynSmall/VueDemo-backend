package com.yangyl.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.yangyl.domain.User;
import com.yyl.common.entity.Constant;

/**
 * shiro session工具类
 * 
 * @author yangyl 2017年2月15日17:53:43
 *
 */
public class ShiroSessionUtil {
	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 
	 * @param key
	 * @param value
	 */
	public static void setSession(String key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
	/**
	 * 获取session
	 * @param key
	 * @return
	 */
	public static Object getSession(String key) {
		Subject currentUser = SecurityUtils.getSubject();
		if (key != null) {
			Session session = currentUser.getSession();
			Object obj = session.getAttribute(key);
			return obj;
		}
		return null;
	}
	/**
	 * 获取当前用户
	 * @return Account
	 */
	public static User getCurrUser(){
		User user = null;
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if(null != session){
			Object obj = session.getAttribute(Constant.SYSTEM_USER);
			if(null != obj && obj instanceof User){
				try{
					user = (User) obj;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return user;
	}

	//初始化session过期时间
	public static void initSession() {
		Session session = SecurityUtils.getSubject().getSession();
		session.setTimeout(-1000l); //-1000l永不超时
	}
}
