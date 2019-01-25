package com.cloud.config.client;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@RefreshScope
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class ConfigClientApplication {
    public static void main(String[] args) {
//        new SpringApplicationBuilder(ConfigClientApplication.class).web(WebApplicationType.SERVLET).run(args);
        SpringApplication.run(ConfigClientApplication.class,args);
    }
}
