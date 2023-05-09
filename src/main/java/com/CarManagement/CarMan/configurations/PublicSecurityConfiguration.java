//package com.CarManagement.CarMan.configurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class PublicSecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain publicSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .requestMatchers().antMatchers("/signup", "/static/**", "/webjars/**")
//                .and()
//                .authorizeRequests().anyRequest().permitAll()
//                .and()
//                .formLogin().and()
//                .logout();
//        return http.build();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
