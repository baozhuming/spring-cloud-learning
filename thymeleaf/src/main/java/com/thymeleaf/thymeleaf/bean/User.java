package com.thymeleaf.thymeleaf.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "t_user")
public class User {
    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    private String userId;

    private String phone;
    private String username;
    private Integer sex;
    @Column(name = "t_password")
    private String password;
    private String eMail;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public User(String userId, String phone, String username, Integer sex, String password, String eMail) {
        this.userId = userId;
        this.phone = phone;
        this.username = username;
        this.sex = sex;
        this.password = password;
        this.eMail = eMail;
    }
}
