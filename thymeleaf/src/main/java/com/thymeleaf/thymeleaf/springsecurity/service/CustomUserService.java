package com.thymeleaf.thymeleaf.springsecurity.service;

import com.thymeleaf.thymeleaf.bean.Person;
import com.thymeleaf.thymeleaf.jpa.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义实现的UserDetailsService接口，想比JDBC用户和内存用户，能更灵活的获取多种数据库的数据
 * 1.实现UserDetailsService接口
 */
public class CustomUserService implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;

    /**
     * 重写方法
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByName(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new User(person.getName(),person.getAddress(),authorities);
    }

    /**
     * 3.需要注册CustomUserSerice到容器中
     * @return
     */
    @Bean
    UserDetailsService customUserService(){
        return new CustomUserService();
    }
}
