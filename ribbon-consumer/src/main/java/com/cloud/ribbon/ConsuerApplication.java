package com.cloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication//该注解包含SpringBootApplication,EnableDiscoveryClient,EnableCiruitBreaker
public class ConsuerApplication {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsuerApplication.class,args);
    }
}
