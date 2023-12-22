package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.dto.CountryResponse;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CountryServiceTest {

	@Autowired
	private CountryService countryService;

	@SuppressWarnings("deprecation")
	@Test
	public void retrieveCorrectCountries() {
		List<CountryResponse> testCountries = new ArrayList<>();
		testCountries.add(CountryResponse.builder().countryId((long) 1).name("Kenya").code("+254").build());
		Mockito.when(countryService.getCountries()).thenReturn(testCountries);
		String countryName = null;
		if (countryService.getCountries().size() > 0) {
			countryName = countryService.getCountries().get(0).getName();
		}
		Assert.assertEquals("Kenya", countryName);
	}
}
