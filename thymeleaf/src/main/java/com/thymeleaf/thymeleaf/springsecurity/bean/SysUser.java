package com.thymeleaf.thymeleaf.springsecurity.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class SysUser implements UserDetails {//实现UserDetails接口，用户实体即为Spring Security所使用的用户
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)//配置用户和角色多对多关系
    private List<SysRole> roles;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//重写方法，将用户角色作为权限
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<SysRole> roles = this.getRoles();
        for (SysRole role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public SysUser() {
    }

    public SysUser(Long id, String username, String password, List<SysRole> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public SysUser(String username, String password, List<SysRole> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
