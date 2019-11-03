package com.example.countries.controllers;

import com.example.countries.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class Controller {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping(path = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCountryInfo(@PathVariable("code") String code) {
        String result = "";
        try {
            result = countryRepository.findByCode(code).toString();
        } catch (DataAccessResourceFailureException ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", ex);
        } catch (NullPointerException exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "INVALID_COUNTRY_CODE", exc);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}