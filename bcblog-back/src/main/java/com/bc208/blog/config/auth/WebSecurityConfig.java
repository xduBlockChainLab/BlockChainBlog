package com.bc208.blog.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    @Qualifier("UsersServiceImpl")
    private UserDetailsService usersService;

    @Autowired
    @Qualifier("AdminServiceImpl")
    private UserDetailsService adminService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //禁用Session
                .and()
                .authorizeRequests() // 进行认证请求的配置
                .antMatchers("/bc208/login").anonymous()
                .antMatchers("/bc208/register").anonymous()
                .antMatchers("/bc208/ForgotPassword").anonymous()
                .antMatchers("/admin/login").anonymous()
                .antMatchers("/admin/register").anonymous()
                .antMatchers("/admin/ForgotPassword").anonymous()
                .antMatchers("/application/submit").anonymous()
                .anyRequest().authenticated()
                .and()
                .cors()
        ;
        //把token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(it -> it.authenticationEntryPoint(((httpServletRequest, httpServletResponse, e) -> {
            String msg = "{\"code\": 5000,  \"msg\": \"User not logged in.\"}";
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write(msg);
            writer.flush();
            writer.close();
        })));

        http.exceptionHandling(it -> it.accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
            String msg = "{\"code\": 5000,  \"msg\": \"User cannot access.\"}";
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write(msg);
            writer.flush();
            writer.close();
            }));
        }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //自定义provider及service，一套身份认证
        auth.authenticationProvider(getUserAuthenticationProvider())
                //使用系统自带provider，及自定义service，另一套认证
                .userDetailsService(usersService).passwordEncoder(bcryptPasswordBean());
        auth.authenticationProvider(getAdminAuthenticationProvider())
                .userDetailsService(adminService).passwordEncoder(bcryptPasswordBean());
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordBean(){
        return new BCryptPasswordEncoder();
    }


    public DaoAuthenticationProvider getUserAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptPasswordBean());
        provider.setUserDetailsService(usersService);
        return provider;
    }

    public DaoAuthenticationProvider getAdminAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptPasswordBean());
        provider.setUserDetailsService(adminService);
        return provider;
    }

}
