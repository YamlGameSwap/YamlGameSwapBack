package com.yamlgameswap.back.service.interceptor;

import com.yamlgameswap.back.service.interceptor.interceptors.AuthInterceptorService;
import com.yamlgameswap.back.service.interceptor.interceptors.IPInterceptorService;
import com.yamlgameswap.back.service.interceptor.interceptors.LanguageInterceptorService;
import com.yamlgameswap.back.service.interceptor.interceptors.TokenInterceptorService;
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
    @Autowired
    private AuthInterceptorService authInterceptorService;
    @Autowired
    private TokenInterceptorService tokenInterceptorService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptorService)
                .addPathPatterns("/api/**");

        registry.addInterceptor(languageInterceptorService)
                .addPathPatterns("/api/**");

        registry.addInterceptor(ipInterceptorService)
                .addPathPatterns("/api/**");

        registry.addInterceptor(tokenInterceptorService)
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/user/login",
                        "/45");
    }
}
