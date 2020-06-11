package com.example.nationinfo;

public class Country {

    String countryCode;
    String countryName;
    Double areaInSqKm;
    Long population;


    public Country(String countryCode, String countryName, Double areaInSqKm, Long population) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.areaInSqKm = areaInSqKm;
        this.population = population;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(Double areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
