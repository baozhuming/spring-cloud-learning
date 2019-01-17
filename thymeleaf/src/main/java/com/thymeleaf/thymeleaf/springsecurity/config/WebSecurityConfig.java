package com.thymeleaf.thymeleaf.springsecurity.config;

import com.thymeleaf.thymeleaf.springsecurity.service.CustomUserService;
import com.thymeleaf.thymeleaf.springsecurity.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //JDBC中的用户----1
    @Autowired
    DataSource dataSource;
//    @Autowired
//    CustomUserService customUserService;
    /**
     * 用户认证
     * 认证需要有一套用户数据的来源，而授权则是对于某个用户相应的角色权限，通过重写configure(AuthenticationManagerBuilder auth)实现
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //1.内存用户
        // 使用auth.inMemoryAuthentication()方法即可添加在内存中的用户，并可给用户指定角色权限。
//        auth.inMemoryAuthentication()
//                .withUser("wyf").password("wyf").roles("ROLE_ADMIN")
//                .and()
//                .withUser("wisely").password("wisely").roles("ROLE_USERS");
        //2.JDBC中的用户
        //(1)SpringSecurity默认了数据库结构
        //查询语句如下public static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password,enabled from users where username = ?";
        //public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select username,authority from authorities where username = ?";
        //auth.jdbcAuthentication().dataSource(dataSource);

        //(2)也可以自定义查询用户和权限的SQL语句
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,true " +
//                        "from myusers where username = ?")
//                .authoritiesByUsernameQuery("select username,role " +
//                        "from roles where username = ?");

        //3.普通用户
        //上面两种用户和权限的获取方式只限于内存或者JDBC，数据访问方式可以是多种多样的，可以是非关系型数据库，也可以是常用的JPA等
        //这时需要自定义实现UserDetailsService接口，上面的内存中用户及JDBC用户就是UserDetailsService的实现
        /**
         * 1.新建类CustomUserService实现UserDetailsService，
         * 2.在类CustomUserService中重写loadUserByUsername方法，并且根据自己的方式写查询数据
         * 3.注册UserDetailsService   Bean到容器中
         * 4.auth.userDetailsService(customUserService);
         */
//        auth.userDetailsService(customUserService);
        auth.userDetailsService(sysUserService()).passwordEncoder(passwordEncoder());
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()//通过authorizeRequests方法来开始请求权限配置。
//        .antMatchers("/admin/**").hasRole("ROLE_ADMIN")//请求匹配/admin/**，知有拥有ROLE_ADMIN角色的用户才可访问
//        .antMatchers("/user/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")//请求匹配/user/**,拥有ROLE_ADMIN或者ROLE_USER角色的用户都可以访问
//        .anyRequest().authenticated();//其余所有的请求都需要认证后(登陆后)才可访问.
//    }

    /**
     * 定制登陆行为
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();//解决springboot不能访问iframe的设置
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()//通过formLogin方法定制登陆操作
                .loginPage("/login")//使用loginPage方法定制登陆页面的访问地址
                .defaultSuccessUrl("/home")//指定登陆后转向的页面
                .failureUrl("/login?error")//指定登陆失败后转向的页面
                .permitAll()
                .and()
                .rememberMe()//开启cookie存储用户信息
                .tokenValiditySeconds(1209600)//指定cookie有效期为1209600秒，即两个星期
                .key("myKey")//指定cookie中的私钥
                .and()
                .logout()//使用logout方法定制注销行为
//                .logoutUrl("/custom-logout")//指定注销的URL路径
                .logoutSuccessUrl("/login")//指定注销成功后转向的页面
                .permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/bootstrap/**","/images/**","/js/**","/404.html","/jquery**.js","/css/**","/regist","/websocket","/swagger-resources/**", "/swagger-ui.html/**","*.html","/layui2/**");
    }
    @Bean
    UserDetailsService sysUserService(){
        return new SysUserService();
    }
}
