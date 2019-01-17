package com.cloud.ribbon.command.service;

import com.cloud.ribbon.bean.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 同步请求
     * @param id
     * @return
     */
    @HystrixCommand
    public User getUserById(Long id){
        return restTemplate.getForObject("http://USER-SERVICE/users/{1}",User.class,id);
    }

    /**
     * 异步请求
     * @param id
     * @return
     */
    @HystrixCommand
    public Future<User> getUserByIdAsync(final String id){
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://USER-SERVICE/users/{1}",User.class,id);
            }
        };
    }
}
