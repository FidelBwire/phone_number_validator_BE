package com.app.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.app.service.CountryService;

@Profile("test")
@Configuration
public class TestConfiguration {

	@Bean
	@Primary
	public CountryService countryService() {
		return Mockito.mock(CountryService.class);
	}
}
