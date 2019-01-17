package com.thymeleaf.thymeleaf.transactional;

import com.thymeleaf.thymeleaf.bean.Person;

/**
 * 事务测试
 */
public interface TransActionalService {
    public Person ssavePersonWithRollBack(Person person);
    public Person savePersonWithoutRollBack(Person person);
}
