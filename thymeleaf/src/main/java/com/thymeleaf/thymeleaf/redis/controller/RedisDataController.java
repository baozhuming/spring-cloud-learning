package com.thymeleaf.thymeleaf.redis.controller;

import com.thymeleaf.thymeleaf.redis.bean.RedisPerson;
import com.thymeleaf.thymeleaf.redis.dao.RedisPersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisDataController {
    @Autowired
    RedisPersonDao redisPersonDao;
    @RequestMapping("/set")
    public void set(){
        RedisPerson person = new RedisPerson("1","wfy",32);
        redisPersonDao.save(person);
        redisPersonDao.stringRedisTemplateDemo();
    }

    @RequestMapping("/getStr")
    public String getStr(){
        return redisPersonDao.getString();
    }

    @RequestMapping("/getPerson")
    public RedisPerson getPerson(){
        return redisPersonDao.getRedisPerson();
    }
}
