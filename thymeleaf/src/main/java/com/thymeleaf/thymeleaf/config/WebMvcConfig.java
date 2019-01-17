package com.thymeleaf.thymeleaf.config;

import com.thymeleaf.thymeleaf.interceptor.InterceptorConf;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@Component
@Configuration
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@EnableAsync  //服务器推送消息方式2：跨浏览器的 --3
@EnableScheduling  //服务器推送消息方式2：跨浏览器的  --4
@EnableWebSocketMessageBroker //
@EnableCaching//开启缓存支持
public class WebMvcConfig implements WebMvcConfigurer {//一般不继承WebMvcConfigurationSupport类，这个类有个条件标签，会导致其他配置失效

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/toSse").setViewName("/sse");
        registry.addViewController("/toAsync").setViewName("/async/async");
        registry.addViewController("/ws").setViewName("/websocket/ws");
        registry.addViewController("/regist").setViewName("regist");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/logiframe").setViewName("/iframe/logiframe");

    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 自定义拦截器:2
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns:添加拦截规则
        //excludePathPatterns:排除拦截规则
        InterceptorRegistration interceptor = registry.addInterceptor(getInterceptorConf());
        interceptor.excludePathPatterns("/error");
        interceptor.excludePathPatterns("/login**");

        interceptor.addPathPatterns("/**");

    }

    /**
     * 自定义拦截器：3
     * @return
     */
    @Bean
    public InterceptorConf getInterceptorConf(){
        return new InterceptorConf();
    }

    /**
     * 解决跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // **代表所有路径
                         .allowedOrigins("*") // allowOrigin指可以通过的ip，*代表所有，可以使用指定的ip，多个的话可以用逗号分隔，默认为*
                         .allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE") // 指请求方式 默认为*
                         .allowCredentials(false) // 支持证书，默认为true
                         .maxAge(3600) // 最大过期时间，默认为-1
                         .allowedHeaders("*");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));//404是error，不是Exception，要单独设置
            }
        };
    }

    //获取端口
//    @Value("${http.port}")
//    private Integer httpPort;
//    @Value("${server.port}")
//    private Integer httpsPort;
    //以下两个方法主要配置设置可以以http://localhost:8080/th/打开
    //或者可以hppts://localhost:8443/th/打开
    /*设置方式1
    @Bean
    public ServletWebServerFactory servletContainer(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }
    private Connector createStandardConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(httpPort);

        return connector;
    }


    //设置方式2
    @Bean
    public TomcatServletWebServerFactory serverContainer(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(httConnector());
        return tomcat;
    }
    @Bean
    public Connector httConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(httpPort);
        connector.setSecure(false);
        //监听到http的端口号后直接转向到的https的端口号
        connector.setRedirectPort(httpsPort);
        return connector;

    }
    */
    //    @Bean
//    public ServletListenerRegistrationBean<MySessionListener> sessionListenerBean(){
//        ServletListenerRegistrationBean<MySessionListener> sessionListner = new ServletListenerRegistrationBean<MySessionListener>(new MySessionListener());
//        return sessionListner;
//    }

    /**
     * 文件上传设置
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(50*1024*1024);//设置最大上传
        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        commonsMultipartResolver.setResolveLazily(true);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }
}
