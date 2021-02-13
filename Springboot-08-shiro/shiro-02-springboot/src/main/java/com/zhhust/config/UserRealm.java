package com.zhhust.config;

import com.zhhust.pojo.User;
import com.zhhust.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/*
* 自定义的Realm
*       by extends AuthorizingRealm
*       类似于SpringSecurity里面的自定义配置类
* */
public class UserRealm  extends AuthorizingRealm {
    @Resource
    UserServiceImpl userService;

    //1. 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了==>doGetAuthorizationInfo授权");
        //根据数据库数据，授予用户不同的权限
        //通过静态方法拿到当前用户对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        System.out.println(currentUser);
        //巧妙关联：SimpleAuthorizationInfo类能够授予权限，让用户能够通过ShiroConfig中过滤器的拦截
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(currentUser.getPerms());
        return simpleAuthorizationInfo;
        //return null;//return表示没有权限，会跳转未授权页面
    }

    //2. 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了==>doGetAuthenticationInfo认证");

        //验证用户的账号、密码（从DB获取用户信息）
        //巧妙联动：下面的Token就是ShiroController里的subject.login(usernamePasswordToken)提供的
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user = userService.queryUserByName(token.getUsername());
        //验证用户名：
        //      返回null在这里代表抛出UnknownAccountException异常
        if (user ==null){//数据库中没有查询到该用户，即：不存在
            return null;
        }
        //密码认证：传入密码，Shiro自动帮我们认证
        //      加密源码：AuthorizingRealm-(父类)->AuthenticatingRealm-(方法)->getCredentialsMatcher(),
        //                该方法返回的是默认的加密机制
        //      可以自定义加密，默认的时简单加密机制，实现查看getCredentialsMatcher()源码
        //      密码错误在这里会抛出IncorrectCredentialsException异常
        return new SimpleAuthenticationInfo(user, user.getPassword(),"");
    }
}
