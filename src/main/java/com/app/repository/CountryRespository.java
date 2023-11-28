package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Country;

public interface CountryRespository extends JpaRepository<Country, Long> {

}
