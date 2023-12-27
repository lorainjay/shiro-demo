package cn.tjut.shirosp.realm;

import cn.tjut.shirosp.domain.User;
import cn.tjut.shirosp.service.UserService;
import cn.tjut.shirosp.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {


    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo
    doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入自定义授权方法");
//1创建对象，存储当前登录的用户的权限和角色
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//2存储角色
        info.addRole("admin");
//返回
        return info;
    }
    /**
     * 自定义身份验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 1. 获取用户登陆信息

        String name = authenticationToken.getPrincipal().toString();
        // 2。 调用业务层获取用户登陆信息
        User user = userService.getUserInfoByName(name);
        // 3. 非空判断，将数据封装
        if(user != null){
            return new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    user.getPwd(),
                    ByteSource.Util.bytes("salt"),
                    name
            );
        }

        return null;
    }
}
