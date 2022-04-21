package com.training.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/hello").setViewName("/greet");
		registry.addViewController("/login").setViewName("/login");
	}
	
}
