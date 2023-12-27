package cn.tjut.shirosp.config;

import cn.tjut.shirosp.realm.MyRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ShiroConfig {

    @Autowired
    MyRealm myRealm;
    @Bean
    public DefaultSecurityManager defaultSecurityManager(){
        //        1. 创建DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        2. 创建加密对象,配置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
//          2.1  加密
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(3);
//        3. 将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(matcher);
//        4. 将myRealm存入DefaultWebSecurityManager对象
        defaultWebSecurityManager.setRealm(myRealm);
//4.5 设置  rememberMe
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
//        5. 返回DefaultWebSecurityManager
        log.info("DefaultWebSecurityManager 初始化成功");
        return defaultWebSecurityManager;
    }
    //cookie 属性设置
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
//设置跨域
//cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        return cookie;
    }

    //创建 Shiro 的  cookie 管理对象
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new
                CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition defaultShiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
//        无需认证
        defaultShiroFilterChainDefinition.addPathDefinition("/myController/userLogin","anon");//表示这个为公共资源 一定是在受限资源上面
        defaultShiroFilterChainDefinition.addPathDefinition("/myController/login","anon");
        // 登出
        defaultShiroFilterChainDefinition.addPathDefinition("/logout","logout");
//        需要认证
        defaultShiroFilterChainDefinition.addPathDefinition("/**","authc");//表示这个受限资源需 要认证和授权
        //添加存在用户的过滤器（rememberMe）
        defaultShiroFilterChainDefinition.addPathDefinition("/**", "user");
        return defaultShiroFilterChainDefinition;
    }
}
