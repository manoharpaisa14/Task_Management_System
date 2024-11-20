package com.task_management.operations_tm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicSecurityApi {
	    // Configure in-memory user details with basic authentication
	    @Bean
	    public InMemoryUserDetailsManager userDetailsService() {
	        UserDetails user = User.withDefaultPasswordEncoder() // Password encoder for demo purposes
	                .username("admin") // Username
	                .password("admin123") // Password
	                .roles("USER") // User role
	                .build();

	        return new InMemoryUserDetailsManager(user);
	    }

	    // Configure security filter chain
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf().disable() // Disable CSRF for simplicity (for APIs, typically disabled)
	            .authorizeRequests()
	                .requestMatchers("/task/**").authenticated()  // Secure `/task` endpoints
	                .anyRequest().permitAll()  // Allow all other requests
	            .and()
	            .httpBasic();  // Enable HTTP Basic Authentication

	        return http.build();
	    }
	}
