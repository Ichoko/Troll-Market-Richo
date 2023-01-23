package com.trollmarket.TrollMarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver(){
        var session= new SessionLocaleResolver();
        var indonesia = new Locale("id","ID");
        session.setDefaultLocale(indonesia);
        return session;
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:home/index");
        registry.addViewController("/profile").setViewName("forward:profile/index");
        registry.addViewController("/shop").setViewName("forward:shop/index");
        registry.addViewController("/merchandise").setViewName("forward:merchandise/index");
        registry.addViewController("/shipper").setViewName("forward:shipper/index");
        registry.addViewController("/myChart").setViewName("forward:myChart/index");
        registry.addViewController("/admin").setViewName("forward:admin/index");
        registry.addViewController("/history").setViewName("forward:history/index");

    }
}