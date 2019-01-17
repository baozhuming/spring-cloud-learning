package com.thymeleaf.thymeleaf.redis.dao;

import com.thymeleaf.thymeleaf.redis.bean.RedisPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class RedisPersonDao {
    @Autowired
    StringRedisTemplate stringRedisTemplate;//SpringBoot已经配置StringRedisTemplate，直接注入

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String,String> valOpsStr;//可以使用@Resource注解指定stringRedisTemplate，可注入基于字符串的简单属性操作方法
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;//SpringBoot已经配置了RedisTemplate，直接注入
    @Resource(name = "redisTemplate")
    ValueOperations<Object,Object> valOps;//可以使用@Resource注解指定redisTemplate，可注入基于对象的简单属性操作方法
    public void stringRedisTemplateDemo(){//通过set方法，存储字符串类型
        valOpsStr.set("xx","yy");
    }

    public void save(RedisPerson redisPerson){
        valOps.set(redisPerson.getId(),redisPerson);//通过set方法，存储对象类型
    }

    public String getString(){
        return valOpsStr.get("xx");//通过get方法，获取字符串
    }
    public RedisPerson getRedisPerson(){
        return (RedisPerson)valOps.get("1");//通过get方法，获取对象
    }
}
