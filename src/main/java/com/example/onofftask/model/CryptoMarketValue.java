package com.example.onofftask.model;

import java.math.BigDecimal;

public class CryptoMarketValue {

    private String  name;
    private BigDecimal valueInEuros;

    public CryptoMarketValue() {
    }

    public CryptoMarketValue(
        String name,
        BigDecimal valueInEuros
    ) {
        this.name = name;
        this.valueInEuros = valueInEuros;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValueInEuros() {
        return valueInEuros;
    }

    public void setValueInEuros(BigDecimal valueInEuros) {
        this.valueInEuros = valueInEuros;
    }
}
