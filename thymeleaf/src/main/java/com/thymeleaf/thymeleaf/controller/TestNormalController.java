package com.thymeleaf.thymeleaf.controller;

import com.thymeleaf.thymeleaf.service.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 配合测试用例的控制器
 */
@Controller
public class TestNormalController {
    @Autowired
    DemoServiceImpl demoService;

    @RequestMapping("/normal")
    public String testPage(Model model){
        model.addAttribute("msg",demoService.saySomething());
        return "page";
    }
}
