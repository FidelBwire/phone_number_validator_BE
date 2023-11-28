package com.app.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.app.dto.PhoneNumberResponse;

public interface PhoneNumberService {

	Page<PhoneNumberResponse> searchPhoneNumbers(Optional<String> status, Optional<Long> countryId, int page, int size,
			String orderBy, String direction);

}
