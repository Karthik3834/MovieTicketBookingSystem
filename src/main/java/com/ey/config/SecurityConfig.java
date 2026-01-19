package com.ey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ey.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth

						// Auth
						.requestMatchers("/api/auth/**").permitAll()

						// Public read-only
						.requestMatchers(HttpMethod.GET, "/api/movies/**").permitAll()
						.requestMatchers(HttpMethod.GET, "/api/show-seats/**").permitAll()

						// Admin
						.requestMatchers(HttpMethod.POST, "/api/movies").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/theatres").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/screens").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/seats/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/shows").hasRole("ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/show-seats/**").hasRole("ADMIN")

						// User
						.requestMatchers(HttpMethod.POST, "/api/bookings").hasRole("USER")
						.requestMatchers(HttpMethod.POST, "/api/payments").hasRole("USER")
						.requestMatchers(HttpMethod.POST, "/api/reviews").hasRole("USER")
						.requestMatchers(HttpMethod.POST, "/api/bookings/cancel").hasRole("USER")

						// Staff
						.requestMatchers(HttpMethod.POST, "/api/entry").hasRole("STAFF")

						// Everything else
						.anyRequest().authenticated())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(
	        AuthenticationConfiguration configuration) throws Exception {
	    return configuration.getAuthenticationManager();
	}
}
