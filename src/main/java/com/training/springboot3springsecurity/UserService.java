package com.training.springboot3springsecurity;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Configuration
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repo;
	
	@Bean
	PasswordEncoder BCPasswordEncoder(){
	   return new BCryptPasswordEncoder();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var userFromDb = repo.findByUname(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found with this email address."));
        List<GrantedAuthority> authorities = new java.util.ArrayList<>(Collections.emptyList());
        userFromDb.getRoles().forEach(role->{
        	authorities.add((GrantedAuthority)()->role.getRole().name());
        });
        
        return  new User(userFromDb.getUname(), userFromDb.getPassword(), authorities);
	}
	
}