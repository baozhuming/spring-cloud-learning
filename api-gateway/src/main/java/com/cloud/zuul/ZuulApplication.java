package com.cloud.zuul;

import com.cloud.zuul.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**路由器
 */
@SpringCloudApplication
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }

    /**
     * 需要注入拦截器才能生效
     * @return
     */
    @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }

    /**
     * 当服务中心有这样的userservice-v1的服务存在时,可以隐射为/v1/uservice/**
     * @return
     */
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper(){
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)","${version}/${name}");
    }
}
