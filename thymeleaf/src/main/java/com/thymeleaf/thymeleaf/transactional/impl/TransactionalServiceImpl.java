package com.thymeleaf.thymeleaf.transactional.impl;

import com.thymeleaf.thymeleaf.bean.Person;
import com.thymeleaf.thymeleaf.jpa.PersonRepository;
import com.thymeleaf.thymeleaf.transactional.TransActionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionalServiceImpl implements TransActionalService {
    @Autowired
    PersonRepository personRepository;//可以直接注入PersonRepository的Bean
    @Transactional(rollbackFor = {IllegalArgumentException.class})//使用注解的rollbackFor属性，指定特定异常时，数据回滚
    public Person ssavePersonWithRollBack(Person person) {
        Person p = personRepository.save(person);
        if(person.getName().equals("云飞")){
            throw new IllegalArgumentException("王云飞已存在,数据将回滚!");//硬编码手动触发异常
        }
        return p;
    }

    @Transactional(noRollbackFor = {IllegalArgumentException.class})//使用注解的noRollbackFor属性，指定特定异常时，数据回滚
    public Person savePersonWithoutRollBack(Person person) {
        Person p = personRepository.save(person);
        if(person.getName().equals("王云飞")){
            throw new IllegalArgumentException("王云飞虽存在，数据将不会回滚！");
        }
        return p;
    }
}
