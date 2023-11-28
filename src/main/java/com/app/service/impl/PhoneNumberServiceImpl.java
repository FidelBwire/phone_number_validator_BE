package com.app.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.app.dto.PhoneNumberResponse;
import com.app.exception.ResourceNotFoundException;
import com.app.model.view.PhoneNumbersView;
import com.app.repository.CountryRespository;
import com.app.repository.PhoneNumbersViewRepository;
import com.app.service.PhoneNumberService;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

	@Autowired
	private PhoneNumbersViewRepository phoneNumbersRepository;

	@Autowired
	private CountryRespository countryRespository;

	@Override
	public Page<PhoneNumberResponse> searchPhoneNumbers(Optional<String> status, Optional<Long> countryId, int page,
			int size, String orderBy, String direction) {
		Order order = direction.equalsIgnoreCase("descending") ? Order.desc(orderBy) : Order.asc(orderBy);
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(order));

		List<PhoneNumbersView> phoneNumbers;
		Long count;

		if (countryId.isPresent() || status.isPresent()) {
			PhoneNumbersView phoneNumbersView = PhoneNumbersView.builder().build();

			if (countryId.isPresent()) {
				Long selectedCountryId = countryId.get();

				if (!countryRespository.existsById(selectedCountryId)) {
					throw new ResourceNotFoundException("Country " + selectedCountryId + " not found");
				}

				phoneNumbersView.setCountryId(selectedCountryId);
			}

			if (status.isPresent()) {
				String selectedStatus = StringUtils.capitalize(status.get().toLowerCase());
				if (!Arrays.asList("Valid", "Invalid").contains(selectedStatus)) {
					throw new ResourceNotFoundException("Invalid status");
				}
				phoneNumbersView.setStatus(selectedStatus);
			}

			Example<PhoneNumbersView> phoneNumbersViewExample = Example.of(phoneNumbersView);
			phoneNumbers = phoneNumbersRepository.findAll(phoneNumbersViewExample, pageRequest).getContent();
			count = phoneNumbersRepository.count(phoneNumbersViewExample);
		} else {
			phoneNumbers = phoneNumbersRepository.findAll(pageRequest).getContent();
			count = phoneNumbersRepository.count();
		}

		List<PhoneNumberResponse> responses = phoneNumbers.stream().map(this::parsePhoneNumberResponse).toList();
		return new PageImpl<>(responses, pageRequest, count);
	}

	private PhoneNumberResponse parsePhoneNumberResponse(PhoneNumbersView phoneNumber) {
		return PhoneNumberResponse.builder().phoneNumber(phoneNumber.getPhone()).countryId(phoneNumber.getCountryId())
				.customerId(phoneNumber.getCustomerId()).customer(phoneNumber.getCustomer())
				.country(phoneNumber.getCountry()).status(phoneNumber.getStatus()).build();
	}

}
