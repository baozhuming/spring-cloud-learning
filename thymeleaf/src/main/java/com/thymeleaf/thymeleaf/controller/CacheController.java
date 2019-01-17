package com.thymeleaf.thymeleaf.controller;

import com.thymeleaf.thymeleaf.bean.Person;
import com.thymeleaf.thymeleaf.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
    @Autowired
    CacheService cacheService;
    @RequestMapping("/put")
    public Person put(@RequestBody Person person){
        return cacheService.save(person);
    }
    @RequestMapping("/able")
    public Person cacheable(Person person){
        return cacheService.findOne(person);
    }
    @RequestMapping("/evit")
    public String evit(Integer id){
        cacheService.remove(id);
        return "ok";
    }
}
