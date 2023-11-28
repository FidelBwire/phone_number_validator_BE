package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private RestAccessDeniedHandler accessDeniedHandler;

	@Autowired
	private ApiAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers(HttpMethod.OPTIONS, "/**").requestMatchers(HttpMethod.GET,
				"/phone-numbers/**", "/countries/**");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> {
			try {
				auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
						.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
						.authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return http.build();
	}
}
