package com.training.userservice.configurations;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserConfig {

	@LoadBalanced
	@Bean
	public RestTemplate initRestTemplat() {
		return new RestTemplate();
	}
	
	
	
}
