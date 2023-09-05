//package com.example.demo.controller;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//@Configuration
//@EnableWebSecurity
//
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder)
//                .withUser("user1")
//                .password(passwordEncoder.encode("123"))
//                .roles("ADMIN");
//    }
//
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable().cors().disable();
//
//        httpSecurity.authorizeRequests()
//                .antMatchers("/chi-tiet-san-pham").permitAll()
//                .anyRequest().authenticated();
//
//        httpSecurity.httpBasic();
//    }
//}
