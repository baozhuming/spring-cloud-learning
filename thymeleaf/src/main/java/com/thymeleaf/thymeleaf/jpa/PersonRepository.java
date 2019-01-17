package com.thymeleaf.thymeleaf.jpa;

import com.thymeleaf.thymeleaf.bean.Person;
import com.thymeleaf.thymeleaf.jpa.CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 或者继承JpaRepository<Person,String>,
 * CustomRepository是自定义的查询器，功能如下：String类型的使用like，其他类型值用=
 */
public interface PersonRepository extends  JpaRepository<Person,Integer>,CustomRepository<Person,Integer> {
    //使用方法名查询，接受一个name参数，返回值为列表
    List<Person> findByAddress(String address);
    //使用方法名查询，接受name和address，返回值为单个对象
    Person findByNameAndAddress(String name,String address);
    //使用@Query查询，参数按照名称绑定
    @Query("select p from Person p where p.name=:name and p.address =:address")
    Person withNameAndAddressQuery(@Param("name") String name,@Param("address") String address);
    //使用@NamedQuery查询，请注意在实体类中做的@NamedQuery的定义
    List<Person> withNameAndAddressNameQuery(String name,String address);
    //通过名称查找
    Person findByName(String name);
}
