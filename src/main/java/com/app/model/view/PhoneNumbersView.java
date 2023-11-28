package com.app.model.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "phone_numbers_view")
public class PhoneNumbersView {

	@Id
	private Long id;
	private String phoneNumber;
	private Long countryId;
	private Long customerId;
	private String customer;
	private String country;
	private String status;
}
