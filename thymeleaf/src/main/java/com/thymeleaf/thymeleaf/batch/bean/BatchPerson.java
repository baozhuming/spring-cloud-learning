package com.thymeleaf.thymeleaf.batch.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
@Entity(name = "BATCH_PERSON")
public class BatchPerson {
    @Id
    @GeneratedValue
    private Long id;
    @Size(max = 10,min = 2)//使用JSR-303注解来校验数据
    private String name;
    private int age;
    private String nation;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
