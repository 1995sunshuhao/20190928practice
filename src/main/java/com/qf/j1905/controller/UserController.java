package com.qf.j1905.controller;

import com.qf.j1905.domain.User;
import com.qf.j1905.service.UserCodeService;
import com.qf.j1905.service.UserService;
import com.qf.j1905.utils.EmailUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserCodeService userCodeService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "reguser")
    public String regUser(@RequestBody User user){
        Integer count=userService.saveUser(user);
        return count==1?"添加成功":"添加失败";
    }
    @RequestMapping(value = "sendcode")
    public boolean sendCode(@RequestBody String userMail){
        return userCodeService.sendCode(userMail);
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody User user){
        System.out.println(user);
        try{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(user.getUserId()), user.getUserPwd());
            subject.login(token);
            if(subject.isAuthenticated()){
                //        已登陆
                //        用户信息及权限信息的存储（session|| redis）
                return "main";
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("登录失败");
        }
        return "login";
    }
}

