//package com.CarManagement.CarMan.configurations;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//public class AuthenticatedSecurityConfiguration {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public SecurityFilterChain authenticatedSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .requestMatchers(requests -> requests
//                        .antMatchers("/**")
//                )
//                .authorizeRequests(authorize -> authorize
//                        .anyRequest().authenticated()
//                )
//                .formLogin(withDefaults())
//                .logout(withDefaults());
//
//        http.userDetailsService(userDetailsService);
//        return http.build();
//    }
//}
