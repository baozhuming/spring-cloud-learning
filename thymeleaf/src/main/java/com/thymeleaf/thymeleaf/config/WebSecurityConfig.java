package com.thymeleaf.thymeleaf.config;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 聊天室配置：1
 */
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {}
//        extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/","/css/**", "/js/**","/images/**", "/bootstrap/**", "**/favicon.ico", "/login").permitAll()//设置Spirng Security对/和/"login"路径不拦截
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .loginPage("/login")//设置Spring Security的登陆页面访问的路径为/login
//                .defaultSuccessUrl("/websocket/chat")//登陆成功后转向/chat路径
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//    /**
//     *在内存中分别配置两个用户wyf和wisely，密码和用户名一致，角色是USER
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("wyf").password("wyf").roles("USER").and()
//                .withUser("wisely").password("wisely").roles("USER");
//    }
//
//    /**
//     * /resources/static/目录下的静态资源，Spring Security不拦截
//     * @param web
//     * @throws Exception
//     */
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/static/**","**.ico");
//    }
//
//
//}
