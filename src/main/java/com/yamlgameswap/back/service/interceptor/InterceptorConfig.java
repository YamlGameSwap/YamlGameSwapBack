package com.yamlgameswap.back.service.interceptor;

import com.yamlgameswap.back.service.interceptor.interceptors.IPInterceptorService;
import com.yamlgameswap.back.service.interceptor.interceptors.LanguageInterceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LanguageInterceptorService languageInterceptorService;
    @Autowired
    private IPInterceptorService ipInterceptorService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(languageInterceptorService)
                .addPathPatterns("/**");

        registry.addInterceptor(ipInterceptorService)
                .addPathPatterns("/**");
    }
}
