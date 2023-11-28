package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CountryResponse;
import com.app.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping
	public ResponseEntity<List<CountryResponse>> getCountries() {
		List<CountryResponse> countries = countryService.getCountries();
		return new ResponseEntity<List<CountryResponse>>(countries, HttpStatus.OK);
	}

}
