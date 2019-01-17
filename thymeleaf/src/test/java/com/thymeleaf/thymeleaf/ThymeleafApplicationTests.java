package com.thymeleaf.thymeleaf;

import com.thymeleaf.thymeleaf.service.DemoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 测试用例
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration//WebAppConfiguration注解在类上，用来声明加载的ApplicationContext是一个WebApplicationContext。
//它的属性指定的是Web资源的位置，默认为src/main/webapp，本例修改为src/main/resources
@SpringBootTest
public class ThymeleafApplicationTests {
    private MockMvc mockMvc;////MockMvc-模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化

    @Autowired
    private DemoServiceImpl demoService;//可以在测试用例中注入Spring的Bean

    @Autowired
    WebApplicationContext wac;//可注入WebApplicationContext

    @Autowired
    MockHttpSession session;//可注入模拟的http session

    @Autowired
    MockHttpServletRequest request;//可注入模拟的http request

    @Before//在测试开始前进行的初始化工作
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testNormalController()throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/normal"))//模拟向/normal进行get请求
                .andExpect(MockMvcResultMatchers.status().isOk())//预期控制返回状态为200
                .andExpect(MockMvcResultMatchers.view().name("page"))//预期view的名称为page
//                .andExpect(MockMvcResultMatchers.forwardedUrl("/classes/templates/page.html"))//预期页面转向的真正路径为/WEB-INF/classes/views.page.jsp
                .andExpect(MockMvcResultMatchers.model().attribute("msg",demoService.saySomething()));//预期model里的值是demoService.saySomething()返回值hello。
    }

    @Test
    public void testRestController()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/testRest"))//模拟向/testRest进行get请求
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))//预期返回值的媒体类型为text/plain;charset=UTF-8
                .andExpect(MockMvcResultMatchers.content().string(demoService.saySomething()));//预期返回值的内容为demoService.saySomething()返回值hello
    }
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Test
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("testconect","hello word!");
        String test = stringRedisTemplate.opsForValue().get("testconect");
        System.out.println(test);
    }
}

