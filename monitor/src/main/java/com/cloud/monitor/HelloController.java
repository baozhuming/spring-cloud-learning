package com.cloud.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
public class HelloController {
    public static void main(String[] args) {
        SpringApplication.run(HelloController.class,args);
    }
}
