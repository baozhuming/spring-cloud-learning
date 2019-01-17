package com.thymeleaf.thymeleaf.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义Servlet监听器
 */
@WebListener
public class MyServletListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("销毁监听器"+sce.getServletContext());
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("进入ServletContext监听器"+sce.getServletContext());
    }
}
