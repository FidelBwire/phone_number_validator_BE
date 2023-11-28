package com.app.service;

import java.util.List;

import com.app.dto.CountryResponse;

public interface CountryService {

	List<CountryResponse> getCountries();

}
