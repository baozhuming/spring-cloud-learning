package com.thymeleaf.thymeleaf.mongodb.service;

import com.thymeleaf.thymeleaf.mongodb.bean.Custom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomMongoDBRepository extends MongoRepository<Custom,String> {
    Custom findByName(String name);//支持方法名查询
    @Query("{'age': ?0}")//支持@Query查询，查询参数构造JSON字符串即可
    List<Custom> withQueryFindByAge(Integer age);

}
