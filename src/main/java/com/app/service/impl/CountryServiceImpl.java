package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CountryResponse;
import com.app.model.Country;
import com.app.repository.CountryRepository;
import com.app.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public List<CountryResponse> getCountries() {
		List<Country> countries = countryRepository.findAll();
		return countries.stream().map(this::parseCountryResponse).toList();
	}

	private CountryResponse parseCountryResponse(Country country) {
		return CountryResponse.builder().countryId(country.getCountryId()).name(country.getName())
				.code(country.getCode().getCode()).build();
	}

}
