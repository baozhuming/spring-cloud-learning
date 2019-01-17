package com.thymeleaf.thymeleaf.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 自定义过滤器：1
 */
@Order(1)//过滤器顺序,越小则先被执行
@WebFilter(urlPatterns = "/users/*",filterName = "TimeFilter")//过滤规则
public class FilterConf implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter被初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TimeFilter被执行");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter被销毁");
    }
}
