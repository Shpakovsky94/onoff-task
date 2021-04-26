package com.example.onofftask.dto;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class CryptoDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Long id;
    private String name;
    private Double amount;
    private String wallet;
    private String  creationDate;
    private BigDecimal purchaseMarketValue;
    private BigDecimal currentMarketPrice;

    public CryptoDto() {
    }

    public CryptoDto(
        String name,
        Double amount,
        String wallet
    ) {
        this.name = name;
        this.amount = amount;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getPurchaseMarketValue() {
        return purchaseMarketValue;
    }

    public void setPurchaseMarketValue(BigDecimal purchaseMarketValue) {
        this.purchaseMarketValue = purchaseMarketValue;
    }

    public BigDecimal getCurrentMarketPrice() {
        return currentMarketPrice;
    }

    public void setCurrentMarketPrice(BigDecimal currentMarketPrice) {
        this.currentMarketPrice = currentMarketPrice;
    }
}
