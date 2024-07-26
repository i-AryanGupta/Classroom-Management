package com.cam.sercurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity.csrf(csrf-> csrf.disable()).securityMatchers(matcher ->
		matcher.requestMatchers("/api/version1/**",  "/login/**"))
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/version1/registers/**")
				.permitAll().anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.build();
	}
	
	@Bean
	AuthenticationManager getAuthentication(AuthenticationConfiguration authenticationConfiguration) throws Exception
	{
		return authenticationConfiguration.getAuthenticationManager();

	}
}
