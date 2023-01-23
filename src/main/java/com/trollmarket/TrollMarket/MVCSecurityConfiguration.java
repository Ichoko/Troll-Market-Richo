package com.trollmarket.TrollMarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MVCSecurityConfiguration {
    @Bean
//    @Order(2)
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests();
        http.authorizeRequests()
                .antMatchers("/resources/**","/account/**","/api/account/**").permitAll()//html yg boleh diakses atau diperbolehkan tanpa login
                .antMatchers("/home/**").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("seller","buyer")
                .antMatchers("/shipper/**","/account/registerAdmin","/history").hasAuthority("admin")
                .antMatchers("/merchandise/**").hasAuthority("seller")
                .antMatchers("/shop/**","/myCart/**").hasAuthority("buyer")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/account/loginForm")
                .loginProcessingUrl("/authenticating")
                .and().logout()
                .and().exceptionHandling().accessDeniedPage("/account/accessDenied");
        return http.build();
    }
}
