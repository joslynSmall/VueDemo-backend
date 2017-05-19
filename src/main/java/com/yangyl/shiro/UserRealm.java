package com.yangyl.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yangyl.domain.User;
import com.yangyl.service.UserService;
import com.yyl.common.entity.Constant;
import com.yyl.common.utils.rsa.MD5;

@Repository
public class UserRealm extends AuthorizingRealm
{
    public static Logger logger = LoggerFactory.getLogger(UserRealm.class);
    
    @Resource
    private UserService userService;

    /**
     * 用于授权
     * @param principalCollection
     * @return
     */
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
    	String username = (String)principals.getPrimaryPrincipal();  
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        authorizationInfo.setRoles(userService.findRoles(username));  
        authorizationInfo.setStringPermissions(userService.findPermissions(username));  
        return authorizationInfo;  
    }

    /**
     * 用于认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @SuppressWarnings("unused")
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    	AuthenticationInfo authenticationInfo = null;
    	UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
    	//用户名
    	String username = token.getUsername(); 
    	//密码
    	String password = new String(token.getPassword());
        
    	User user = userService.findByUsername(username);
        if(user == null) {  
            throw new UnknownAccountException();//没找到帐号  
        }  
        if(Boolean.TRUE.equals(user.getLocked())) {  
            throw new LockedAccountException(); //帐号锁定  
        }  
        if (user.getPassword().equals(password)) {
        	//设置session失效时间
        	ShiroSessionUtil.initSession();
        	//添加进缓存
        	ShiroSessionUtil.setSession(Constant.SYSTEM_USER, user);
        	
        	authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        } else {
        	throw new IncorrectCredentialsException();//错误认证异常
        }
        
        return authenticationInfo;
    }
    
    //清空缓存
    protected void clearCache() {
    	// TODO Auto-generated method stub
    	PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
    	super.clearCache(principals);
    }

}
