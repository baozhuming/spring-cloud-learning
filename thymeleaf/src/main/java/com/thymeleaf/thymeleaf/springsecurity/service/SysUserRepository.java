package com.thymeleaf.thymeleaf.springsecurity.service;

import com.thymeleaf.thymeleaf.springsecurity.bean.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    SysUser findByUsername(String username);
    SysUser save(SysUser sysUser);
}
