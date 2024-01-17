package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MyConfig {
	
	@Bean
	public UserDetailsService  getUserDetailsService() {
		
		return new userDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(this.getUserDetailsService());
		authenticationProvider.setPasswordEncoder(PasswordEncoder());
		
		return authenticationProvider;
	}
	

}
