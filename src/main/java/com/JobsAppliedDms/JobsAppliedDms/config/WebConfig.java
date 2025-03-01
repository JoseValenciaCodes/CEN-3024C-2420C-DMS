package com.JobsAppliedDms.JobsAppliedDms.config;

/* WebConfig
* Register the SessionInterceptor so it does not apply on register and login routes
* Add more routes as the app grows
* */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    private final SessionInterceptor sessionInterceptor;

    public WebConfig(SessionInterceptor sessionInterceptor)
    {
        this.sessionInterceptor = sessionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(sessionInterceptor)
                .excludePathPatterns("/api/auth/login", "/api/auth/register");
    }
}
