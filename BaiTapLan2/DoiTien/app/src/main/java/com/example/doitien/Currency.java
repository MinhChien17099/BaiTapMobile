package com.example.doitien;

public class Currency {
    private String currencyName;
    private String currencyCode;
    private String description;

    public Currency(String currencyName, String currencyCode, String description) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.description = description;
    }
    public Currency(String currencyName,String currencyCode) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.description = "this is description";
    }
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
