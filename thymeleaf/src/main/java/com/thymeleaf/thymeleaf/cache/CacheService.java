package com.thymeleaf.thymeleaf.cache;

import com.thymeleaf.thymeleaf.bean.Person;

/**
 * 缓存测试
 */
public interface CacheService {
    public Person save(Person person);
    public void remove(Integer id);
    public Person findOne(Person person);
}
