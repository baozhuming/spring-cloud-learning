package com.thymeleaf.thymeleaf.controller;

import com.thymeleaf.thymeleaf.bean.Person;
import com.thymeleaf.thymeleaf.jpa.PersonRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {
    //1 Spring Data JPA已自动注册bean，所以可自动注入
    @Autowired
    PersonRepository personRepository;

    /**
     * 保存
     * saveAll支持批量保存:<S extends T> Iterable<S> save(Iterable<S> entities)
     * @return
     */
    @RequestMapping(value = "/save")
    public Person save(@RequestBody Person person){
        Person p = personRepository.save(person);
        return p;
    }
    @RequestMapping("/q1")
    public List<Person> q1(String address){
        List<Person> people = personRepository.findByAddress(address);
        return people;
    }
    @ApiOperation(value = "添加一条保险公司信息", notes = "添加一条保险公司信息")
    @RequestMapping("/q2")
    public Person q2(String name,String address){
        Person person = personRepository.findByNameAndAddress(name,address);
        return person;
    }

    @RequestMapping("/q3")
    public Person q3(String name,String address){
        Person p = personRepository.withNameAndAddressQuery(name,address);
        return p;
    }

    @RequestMapping("/q4")
    public List<Person> q4(String name, String address){
        List<Person> p = personRepository.withNameAndAddressNameQuery(name,address);
        return p;
    }

    @RequestMapping("/sort")
    public List<Person> sort(String sort){
        List<Person> people = null;
        if(null != sort && !"".equals(sort) && sort.indexOf(",") < 0){
            people = personRepository.findAll(new Sort(Sort.Direction.ASC,sort));
        }else{
            List<String> ss = new ArrayList<String>();
            if(sort.indexOf(",")>=0){
                String[] s = sort.split(",");
                for(int i =0; i < s.length; i++){
                    String sp = s[i];
                    if(null != sp && !"".equals(sp)){
                        ss.add(sp);
                    }
                }
            }else{
                ss.add(sort);
            }
            people = personRepository.findAll(new Sort(Sort.Direction.ASC,ss));
        }

        return people;
    }
    @RequestMapping("/page")
    public Page<Person> page(){
        Page<Person> page = personRepository.findAll(PageRequest.of(1,2));
        return page;
    }

    /**
     * 在实体类中定义的数据类型要用包装类(Long,Integer),而不能使用原始数据类型(long,int)。因为在Spring MVC中，使用原始数据类型会自动初始化为0，而不是空，导致构造条件失败
     * @param person
     * @return
     */
    @RequestMapping("/auto")
    public Page<Person> auto(Person person){
        Page<Person> pagePeople = personRepository.findByAuto(person,PageRequest.of(0,10));
        return pagePeople;
    }
}
