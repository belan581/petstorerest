package com.upemor.petstorerest.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.upemor.petstorerest.service.UserInfoDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserInfoDetailService userInfoDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userInfoDetailService)
		.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
		
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();
		http.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests()
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers(HttpMethod.GET,"/api/user/","/api/pet/","/api/category/","/api/tag/").permitAll()
			.antMatchers(HttpMethod.POST,"/api/order/").permitAll()
		.and().authorizeRequests()
			.antMatchers(HttpMethod.POST,"/api/user/","/api/pet/","/api/category/","/api/tag/").permitAll()
			.antMatchers(HttpMethod.PUT,"/api/user/{id}","/api/pet/{id}","/api/category/{id}","/api/tag/{id}").authenticated()
			.antMatchers(HttpMethod.DELETE,"/api/user/{id}","/api/pet/{id}","/api/category/{id}","/api/tag/{id}").authenticated()
		.and().httpBasic().realmName("Pet Store webapp")
		.and().csrf().disable();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
