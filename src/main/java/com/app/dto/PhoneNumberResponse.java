package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PhoneNumberResponse {

	private String phoneNumber;
	private Long countryId;
	private Long customerId;
	private String customer;
	private String country;
}
