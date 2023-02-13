package com.onlinestudy.system.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public CorsFilter getCorsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //添加哪些HTTP方法可以跨域
        corsConfiguration.addAllowedMethod("*");
        //添加哪些请求可以跨域
        corsConfiguration.addAllowedOrigin("*");
        //添加哪些头信息可以跨域
        corsConfiguration.addAllowedHeader("*");
        //允许跨域发送cookie
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
