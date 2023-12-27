package cn.tjut.shirosp.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("myController")
public class MyController {
    //跳转登录页面
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @GetMapping("userLogin")
    public String login(@RequestParam("name") String username, @RequestParam("pwd") String password, @RequestParam(defaultValue = "false") boolean rememberMe, HttpSession session){
        Subject subject  = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
        try {
            subject.login(token);
            session.setAttribute("user",token.getPrincipal().toString());
            return "main";
        } catch (Exception e) {
            e.printStackTrace( );
            return "登陆失败";
        }
    }
    //登录认证验证  rememberMe
    @GetMapping("userLoginRm")
    public String userLogin(HttpSession session) {
        session.setAttribute("user", "rememberMe");
        return "main";
    }
    //登录认证验证角色
    @RequiresRoles("admin")
    @GetMapping("userLoginRoles")
    @ResponseBody
    public String userLoginRoles() {
        System.out.println("登录认证验证角色");
        return "验证角色成功";
    }
}
