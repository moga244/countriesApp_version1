package com.example.countries.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    Country(){

    }
    public Country(String name, String code, String continent, int population, Integer lifeExpectancy) {
        super();
        this.name = name;
        this.code = code;
        this.continent = continent;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
    }

    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "code")
    private String code;


    @Column(name = "continent")
    private String continent;


    @Column(name = "population")
    private int population;


    @Column(name = "life_expectancy")
    private Integer lifeExpectancy;

    @OneToMany(targetEntity = CountryLanguage.class, mappedBy = "code", orphanRemoval = false, fetch = FetchType.LAZY)
    private List<CountryLanguage> languages;

    @Override
    public String toString() {
        return "{\n" +
                "\"name\": \"" + name + "\"\n" +
                "\"continent\": \"" + continent + "\"\n" +
                "\"population\":" + population + "\n" +
                "\"life_expectancy\":" + lifeExpectancy + "\n" +
                "\"country_language\":\"" + languages.get(0) + "\"\n" +
                "}\n";
    }
}
