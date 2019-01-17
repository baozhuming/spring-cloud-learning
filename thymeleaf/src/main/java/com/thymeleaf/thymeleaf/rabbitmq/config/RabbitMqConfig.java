package com.thymeleaf.thymeleaf.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig implements CommandLineRunner {
    @Autowired
    RabbitTemplate rabbitTemplate;//注入
    @Override
    public void run(String... args) throws Exception {
        //向队列my-queue发送消息
        rabbitTemplate.convertAndSend("my-queue","来自RabbitMQ的问候");
    }
    @Bean//定义目的地,即队列,队列名称为my-queue
    public Queue wiselyQueue(){
        return new Queue("my-queue");
    }
}
