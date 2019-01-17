package com.thymeleaf.thymeleaf.springsecurity.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SysRole {
    @Id
    @GeneratedValue
    private Long id;
    private String name;//角色名称

    public SysRole() {
    }

    public SysRole(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
