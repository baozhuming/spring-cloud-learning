package com.thymeleaf.thymeleaf.cache.impl;

import com.thymeleaf.thymeleaf.bean.Person;
import com.thymeleaf.thymeleaf.jpa.PersonRepository;
import com.thymeleaf.thymeleaf.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 使用spring自身的缓存技术
 */
@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    PersonRepository personRepository;

    @Override
    @CachePut(value = "people",key = "#person.id")//缓存心中的或更新的数据到缓存，其中缓存名称为people，数据的key是person.id
    public Person save(Person person) {
        Person p = personRepository.save(person);
        System.out.println("id，key为："+p.getId()+"数据做了缓存！");
        return p;
    }

    @Override
    @CacheEvict(value = "people")//从缓存people中删除key为id的数据
    public void remove(Integer id) {
        System.out.println("删除了id，key为"+id+"的数据缓存！");
        personRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "people",key = "#person.id")//缓存key为person的id数据到缓存people中
    public Person findOne(Person person) {
        Optional<Person> p = personRepository.findById(person.getId());
        System.out.println("为id，key为："+p.get().getId()+"数据做了缓存！");
        return p.get();
    }
}
