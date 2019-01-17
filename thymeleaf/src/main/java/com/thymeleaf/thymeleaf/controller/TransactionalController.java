package com.thymeleaf.thymeleaf.controller;

import com.thymeleaf.thymeleaf.bean.Person;
import com.thymeleaf.thymeleaf.transactional.TransActionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionalController {
    @Autowired
    TransActionalService transActionalService;
    @RequestMapping("/rollback")
    public Person rollback(Person person){//测试回滚情况
        return transActionalService.ssavePersonWithRollBack(person);
    }

    @RequestMapping("/norollback")
    public Person noRollback(Person person){//测试不回滚情况
        return transActionalService.savePersonWithoutRollBack(person);
    }
}
