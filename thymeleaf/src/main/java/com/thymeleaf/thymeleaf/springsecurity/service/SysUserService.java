package com.thymeleaf.thymeleaf.springsecurity.service;

import com.thymeleaf.thymeleaf.springsecurity.bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SysUserService implements UserDetailsService {
    @Autowired
    SysUserRepository sysUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

}
