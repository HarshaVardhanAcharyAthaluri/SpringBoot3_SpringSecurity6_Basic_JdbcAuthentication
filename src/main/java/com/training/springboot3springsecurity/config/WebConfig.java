package com.training.springboot3springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry reg) {
		reg.addViewController("/home").setViewName("home");
		reg.addViewController("/").setViewName("home");
		reg.addViewController("/greet").setViewName("greet");
		
	}
	
}
