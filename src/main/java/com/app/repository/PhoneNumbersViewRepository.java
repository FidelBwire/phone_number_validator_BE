package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.view.PhoneNumbersView;

public interface PhoneNumbersViewRepository extends JpaRepository<PhoneNumbersView, Long> {

}
