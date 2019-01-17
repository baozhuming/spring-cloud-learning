package com.thymeleaf.thymeleaf.springsecurity.controller;

import com.thymeleaf.thymeleaf.springsecurity.bean.Msg;
import com.thymeleaf.thymeleaf.springsecurity.bean.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String index(Model model){
        Msg msg = new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute(msg);
        SysUser userDetails = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        GrantedAuthority[] authorities = userDetails.getAuthorities().toArray(new GrantedAuthority[0]);
        System.out.println("username:"+username);
        System.out.println("authorities:"+authorities);
        for (GrantedAuthority authority:authorities){
            System.out.println("authority:"+authority);
        }
        return "home";
    }
}
