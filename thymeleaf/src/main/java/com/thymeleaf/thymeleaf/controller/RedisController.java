package com.thymeleaf.thymeleaf.controller;

import com.thymeleaf.thymeleaf.bean.Person;
import com.thymeleaf.thymeleaf.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    CacheService cacheService;
    @RequestMapping("/redis")
    public Person cacheable(Person person){
        return cacheService.findOne(person);
    }
}
