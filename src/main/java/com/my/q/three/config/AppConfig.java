package com.my.q.three.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.my.q.three.aspect.TimeMeasureAspect;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class AppConfig {
     
    @Bean
    public TimeMeasureAspect timeMeasureAspect() {
    	return new TimeMeasureAspect();
    }
    
}
