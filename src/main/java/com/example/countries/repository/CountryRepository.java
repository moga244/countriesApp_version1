package com.example.countries.repository;

import com.example.countries.model.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, Long> {
    Country findByCode(String Code);

    List<Country> findAll();
}


