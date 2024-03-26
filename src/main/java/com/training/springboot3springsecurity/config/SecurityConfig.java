package com.training.springboot3springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.training.springboot3springsecurity.UserRepository;
import com.training.springboot3springsecurity.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	UserRepository repo;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/", "/home", "/save").permitAll()
				.requestMatchers("/greet").hasAnyRole("admin")
				.anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login")
						.permitAll())
				.logout((logout) -> logout.permitAll())
				.csrf(csrf -> csrf.disable());
		return http.build();
	}

}