package com.thymeleaf.thymeleaf.rabbitmq.receivers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReceiver {
    @RabbitListener(queues = "my-queue")//监听发送到my-queue的消息
    public void receiveMessage(String message){
        System.out.println("Received:<"+message+">");
    }
}
