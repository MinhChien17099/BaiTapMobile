package com.example.doitien;

public class Currency {
    private String currencyName;
    private String currencyCode;

    public Currency(String currencyName,String currencyCode) {
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
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

}
