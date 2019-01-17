package com.thymeleaf.thymeleaf.springsecurity.controller;

import com.thymeleaf.thymeleaf.springsecurity.bean.SysRole;
import com.thymeleaf.thymeleaf.springsecurity.bean.SysUser;
import com.thymeleaf.thymeleaf.springsecurity.service.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegistController {
    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @RequestMapping("/regist")
    public SysUser regist(SysUser user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        sysUserRepository.save(user);
        user.setPassword("");
        return user;
    }
}
