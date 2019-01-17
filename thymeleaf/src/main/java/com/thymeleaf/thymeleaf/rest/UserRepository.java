package com.thymeleaf.thymeleaf.rest;

import com.thymeleaf.thymeleaf.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface UserRepository extends JpaRepository<User,String> {
    @RestResource(path = "usernameStartsWith", rel = "usernameStartsWith")
    User findByUsernameStartingWith(@Param("username") String username);
}
