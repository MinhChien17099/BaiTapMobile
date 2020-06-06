package com.example.nationinfo;

public class Country {

    String countryCode;
    String countryName;
    String areaInSqKm;
    String population;

    public Country(String countryCode,String countryName,String areaInSqKm,String population)
    {
        this.countryCode=countryCode;
        this.countryName=countryName;
        this.areaInSqKm=areaInSqKm;
        this.population=population;
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

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String area) {
        this.areaInSqKm = area;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }



}
