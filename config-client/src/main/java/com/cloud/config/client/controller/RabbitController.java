package com.cloud.config.client.controller;

import com.cloud.config.client.config.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RabbitController {
    @Autowired
    private Sender sender;
    @RequestMapping("/send")
    public void send(){
        sender.send();
    }
}
