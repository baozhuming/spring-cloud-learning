package com.cloud.config.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class ZuulConfig {
    @Autowired
    ServerProperties server;
    @Autowired
    JdbcTemplate jdbcTemplate;
    /**
     * 结合config服务器的动态路由
     * @return
     */
    @Bean(name = "zuul.CONFIGURATION_PROPERTIES")
    @RefreshScope
    @ConfigurationProperties("zuul")
    @Primary
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }

    @Bean
    public CustomRouteLocator routeLocator(){
        CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServlet().getServletPrefix(),zuulProperties());
        routeLocator.setJdbcTemplate(jdbcTemplate);
        return routeLocator;
    }

}
