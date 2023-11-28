package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PhoneNumberResponse;
import com.app.service.PhoneNumberService;

@RestController
@RequestMapping("/phone-numbers")
public class PhoneNumbersController {

	@Autowired
	private PhoneNumberService phoneNumberService;

	@GetMapping
	public ResponseEntity<Page<PhoneNumberResponse>> searchPhoneNumbers(
			@RequestParam(name = "status", required = false) Optional<String> status,
			@RequestParam(name = "country", required = false) Optional<Long> countryId,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "100") int size,
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(name = "direction", defaultValue = "descending") String direction) {
		Page<PhoneNumberResponse> phoneNumbers = phoneNumberService.searchPhoneNumbers(status, countryId, page, size,
				orderBy, direction);
		return new ResponseEntity<>(phoneNumbers, HttpStatus.OK);
	}
}
