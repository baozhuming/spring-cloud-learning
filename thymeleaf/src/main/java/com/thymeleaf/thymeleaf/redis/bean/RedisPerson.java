package com.thymeleaf.thymeleaf.redis.bean;

import java.io.Serializable;

public class RedisPerson implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private Integer age;

    public RedisPerson() {
    }

    public RedisPerson(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
